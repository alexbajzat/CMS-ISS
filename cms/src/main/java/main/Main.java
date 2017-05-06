package main;

import config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bjz on 5/5/2017.
 */
public class Main {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    }
}
