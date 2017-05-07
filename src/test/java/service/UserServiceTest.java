package service;

import config.AppConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.service_requests.AddUserRequest;

import static org.junit.Assert.*;

/**
 * Created by bjz on 5/7/2017.
 */
public class UserServiceTest {
    private static UserService userService;

    @BeforeClass
    public static void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        userService = (UserService) context.getBean("userService");
    }

    @Test
    public void add() throws Exception {

    }

    private AddUserRequest initAddRequest() {
        return null;
    }

}