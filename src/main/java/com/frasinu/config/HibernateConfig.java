package com.frasinu.config;

import org.springframework.context.annotation.Configuration;

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
