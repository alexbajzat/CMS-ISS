package service;

import exception.InexistentException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.mysql_db.UserRepositoryDB;
import service.service_requests.AddUserRequest;
import service.service_requests.DeleteUserRequest;
import service.service_requests.UpdateUserRequest;

import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
public class UserService implements IUserService {
    private UserRepositoryDB userRepositoryDB;

    @Autowired
    public UserService setUserRepositoryDB(UserRepositoryDB userRepositoryDB) {
        this.userRepositoryDB = userRepositoryDB;
        return this;
    }

    @Override
    public User add(AddUserRequest addUserRequest) {
        String name = addUserRequest.getName();
        String username = addUserRequest.getUsername();
        String password = addUserRequest.getPassword();

        User user = User.builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .build();

        return userRepositoryDB.create(user);
    }

    @Override
    public void delete(DeleteUserRequest deleteUserRequest) throws InexistentException {
        int id = deleteUserRequest.getId();
        userRepositoryDB.delete(id);
    }

    @Override
    public User update(UpdateUserRequest updateUserRequest) throws InexistentException {
        int id = updateUserRequest.getIdOfUserToUpdate();
        String name = updateUserRequest.getNewName();
        String username = updateUserRequest.getNewUsername();
        String password = updateUserRequest.getNewPassword();

        User user = User.builder()
                .setId(id)
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .build();

        return userRepositoryDB.update(user);
    }

    @Override
    public List<User> getAll() {
        return userRepositoryDB.getAll();
    }
}
