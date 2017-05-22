package com.frasinu.iss;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by bjz on 5/21/2017.
 */
@ComponentScan(basePackages = "com.frasinnu.iss")
public class BaseTestClass {
    protected static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BaseTestClass.class);
}
