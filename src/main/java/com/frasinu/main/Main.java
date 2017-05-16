package com.frasinu.main;

import com.frasinu.config.AppConfig;
import com.frasinu.exception.LoginException;
import com.frasinu.model.User;
import com.frasinu.repository.mysql_db.UserRepository;
import com.frasinu.service.UserService;
import com.frasinu.service.service_requests.DeleteUserRequest;
import com.frasinu.service.service_requests.LoginUserRequest;
import com.frasinu.service.service_requests.RegisterUserRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * Created by bjz on 5/5/2017.
 */
@ComponentScan(basePackages = "com.frasinu")
public class Main {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        UserService userService=applicationContext.getBean(UserService.class,userRepository);
//        List<User> userList = userRepository.getAll();
//        User user = userRepository.create(User.builder()
//                .setPassword("test")
//                .setUsername("dadsa")
//                .setName("sdasd")
//                .build());
        try {
            User user=userService.registerUser(new RegisterUserRequest("test","test","test"));
            userService.checkLogin(new LoginUserRequest("test","test"));
            userService.deleteUser(new DeleteUserRequest(user.getId()));
        } catch (LoginException e) {
            e.printStackTrace();
        }
//        userRepository.delete(user.getId());


    }
}
