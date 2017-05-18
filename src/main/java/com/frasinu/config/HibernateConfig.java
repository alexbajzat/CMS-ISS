package com.frasinu.config;

import com.frasinu.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by bjz on 5/15/2017.
 */
@Configuration
public class HibernateConfig {

//    @Bean
//    public SessionFactory sessionFactory() throws IOException {
//        InputStream inputStream = HibernateConfig.class.getResourceAsStream("/mysql_db.config");
//        Properties dbProps = new Properties();
//        dbProps.load(inputStream);
//
//        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration()
//                .setProperty("hibernate.connection.driver_class", dbProps.getProperty("driver"))
//                .setProperty("hibernate.connection.url", dbProps.getProperty("db"))
//                .setProperty("hibernate.connection.username", dbProps.getProperty("user"))
//                .setProperty("hibernate.connection.password", dbProps.getProperty("password"))
//                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
//                .addAnnotatedClass(User.class);
//
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
//                .build();
//        System.out.println("Hibernate Java Config serviceRegistry created");
//
//        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//
//        return sessionFactory;
//    }
}
