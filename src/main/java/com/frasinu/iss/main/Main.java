package com.frasinu.iss.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by bjz on 5/5/2017.
 */
@ComponentScan(basePackages = "com.frasinu.iss")
public class Main {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
    }
}
