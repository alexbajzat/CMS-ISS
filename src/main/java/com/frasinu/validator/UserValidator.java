package com.frasinu.validator;

import com.frasinu.persistance.model.User;

import javax.xml.bind.ValidationException;

/**
 * Created by Toshiba on 5/8/2017.
 */
public class UserValidator implements Validator<User> {
    public void validare(User obj) throws ValidationException {
        if(obj.getName().equals(""))
            throw new ValidationException("User has no name!");
        if(obj.getName().matches(".*\\d+.*"))
            throw new ValidationException("Name cannot contain digits!");
        if(obj.getUsername().equals(""))
            throw new ValidationException("User has no username!");
        if(obj.getPassword().equals(""))
            throw new ValidationException("User has no password!");
        if(obj.getPassword().length()>20)
            throw new ValidationException("Password must be less than 20 characters!");
        if(obj.getUsername().length()>20)
            throw new ValidationException("Username must be less than 20 characters!");

    }
}
