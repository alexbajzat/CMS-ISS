package com.frasinu.validator;

import com.frasinu.model.Author;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.*;

/**
 * Created by Ericqw on 17.05.2017.
 */
public class AuthorValidatorTest {
    private static AuthorValidator authorValidator;
    @BeforeClass
    public static void init() {
        authorValidator=new AuthorValidator();
    }

    @Test
    public void testValidare() throws ValidationException {
        authorValidator.validare(Author.builder()
        .setId(10)
        .setAffiliation("affiliaton")
        .setEmail("email")
        .build());
    }
    @Test(expected = ValidationException.class)
    public void testNoAfilliation()throws ValidationException{
        authorValidator.validare(Author.builder()
                .setId(10)
                .setAffiliation("")
                .setEmail("email")
                .build());
    }

    @Test(expected = ValidationException.class)
    public void testNoEmail()throws ValidationException{
        authorValidator.validare(Author.builder()
                .setId(10)
                .setAffiliation("afilliation")
                .setEmail("")
                .build());
    }
}