package repository.mysql_db;


import config.AppConfig;
import model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
public class UserRepositoryDBTest {
    private static UserRepositoryDB userRepositoryDB;

    @BeforeClass
    public static void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        userRepositoryDB = (UserRepositoryDB) applicationContext.getBean("userRepositoryDB");
    }

    @Test
    public void create() throws Exception {
        int beforeSize = userRepositoryDB.getAll().size();
        User user = User.builder()
                .setName("test")
                .setPassword("testPass")
                .setUsername("testUser")
                .build();
        User addedUser = userRepositoryDB.create(user);
        List<User> result = userRepositoryDB.getAll();
        Assert.assertTrue(result.size() == beforeSize + 1);
        Assert.assertTrue(addedUser.getName().equals(user.getName()));
        Assert.assertTrue(addedUser.getUsername().equals(user.getUsername()));
        Assert.assertTrue(addedUser.getPassword().equals(user.getPassword()));
        Assert.assertTrue(addedUser.getId() != null);
        Assert.assertTrue(userRepositoryDB.findById(addedUser.getId()).getName().equals(user.getName()));

    }
}