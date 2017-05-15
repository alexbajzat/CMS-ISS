package com.frasinu.main;

import com.frasinu.config.AppConfig;
import com.frasinu.model.User;
import com.frasinu.repository.mysql_db.UserRepository;
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
        List<User> userList = userRepository.getAll();
        User user = userRepository.create(User.builder()
                .setPassword("test")
                .setUsername("dadsa")
                .setName("sdasd")
                .build());
        userRepository.delete(user.getId());
    }
}
