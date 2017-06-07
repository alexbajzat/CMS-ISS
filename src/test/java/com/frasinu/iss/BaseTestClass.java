package com.frasinu.iss;

import com.frasinu.iss.config.AppConfig;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.view.FrasinuApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by bjz on 6/7/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {FrasinuApplication.class})
public class BaseTestClass {
    @Test
    public void smt(){
        System.out.println("ceva");
    }
}
