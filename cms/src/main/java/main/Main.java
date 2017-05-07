package main;

import config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * Created by bjz on 5/5/2017.
 */
public class Main {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println("merge");
    }
}
