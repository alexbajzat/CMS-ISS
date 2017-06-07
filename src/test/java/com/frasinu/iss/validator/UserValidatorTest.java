package com.frasinu.iss.validator;

import com.frasinu.iss.BaseTestClass;
import com.frasinu.iss.persistance.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.ValidationException;

/**
 * Created by bjz on 5/11/2017.
 */
public class UserValidatorTest  extends BaseTestClass{
    private static UserValidator userValidator;

    @BeforeClass
    public static void init() {
        userValidator = new UserValidator();
    }

    @Test
    public void testValidate() throws ValidationException {
        userValidator.validate(User.builder()
                .setId(12)
                .setName("ok")
                .setUsername("username")
                .setPassword("safas123")
                .build());
    }

    @Test(expected = ValidationException.class)
    public void digitUserNameValidateShouldThrowException() throws ValidationException {
        userValidator.validate(User.builder()
                .setId(12)
                .setName("ok12")
                .setUsername("username")
                .setPassword("safas123")
                .build());
    }

    @Test(expected = ValidationException.class)
    public void userPasswordLessThanValidateShouldThrowException() throws ValidationException {
        userValidator.validate(User.builder()
                .setId(12)
                .setName("ok")
                .setUsername("username")
                .setPassword("123")
                .build());
    }

}