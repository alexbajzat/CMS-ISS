package repository.mysql_db;

import exception.DataBaseException;
import exception.InexistentException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import repository.IUserRepository;

import java.sql.*;
import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
public class UserRepositoryDB implements IUserRepository {
    private JdbcTemplate jdbcTemplate;
    private final String SQL_INSERT = "INSERT INTO user_app(name,username,password) VALUES(?,?,?)";
    private final String SQL_FIND_BY_ID = "SELECT id,name,username,password FROM user_app WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM user_app WHERE id=?";
    private final String SQL_UPDATE = "UPDATE user_app SET name = ? , username= ?, password= ? WHERE id=?";
    private final String SQL_SELECT_ALL = "SELECT id,name,username,password FROM user_app";


    @Autowired
    public UserRepositoryDB setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        return this;
    }

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            preparedStatement.setString(i++, user.getName());
            preparedStatement.setString(i++, user.getUsername());
            preparedStatement.setString(i, user.getPassword());

            return preparedStatement;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();

        try {
            return findById(id);
        } catch (InexistentException e) {
            throw new DataBaseException("Incosistent sql insert!" + e);
        }
    }

    @Override
    public void delete(int id) throws InexistentException {
        if (jdbcTemplate.update(SQL_DELETE, new Object[]{id}) == 0) {
            throw new InexistentException("No such user to delete!");
        }
    }

    @Override
    public User update(User user) throws InexistentException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            preparedStatement.setString(i++, user.getName());
            preparedStatement.setString(i++, user.getUsername());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setInt(i, user.getId());
            return preparedStatement;
        }, keyHolder) == 0) {
            throw new InexistentException("No such user to update!");
        }
        return findById(keyHolder.getKey().intValue());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new UserWrapper());
    }

    @Override
    public User findById(int id) throws InexistentException {
        User user = (User) jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, new UserWrapper());
        if (user == null) {
            throw new InexistentException("No such user!");
        }
        return user;
    }

    private class UserWrapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            int i = 1;
            int id = rs.getInt(i++);
            String name = rs.getString(i++);
            String username = rs.getString(i++);
            String password = rs.getString(i);

            return User.builder()
                    .setId(id)
                    .setName(name)
                    .setPassword(password)
                    .setUsername(username)
                    .build();
        }
    }
}
