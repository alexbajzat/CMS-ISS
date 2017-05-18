package com.frasinu.validator;

import com.frasinu.exception.ValidateException;
import com.frasinu.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by Toshiba on 5/8/2017.
 */
@Component
public class UserValidator implements Validator<User> {
    public void validare(User obj) throws ValidateException {
        if(obj.getName().equals(""))
            throw new ValidateException("User has no name!");
        if(obj.getName().matches(".*\\d+.*"))
            throw new ValidateException("Name cannot contain digits!");
        if(obj.getUsername().equals(""))
            throw new ValidateException("User has no username!");
        if(obj.getPassword().equals(""))
            throw new ValidateException("User has no password!");
        if(obj.getPassword().length()>20)
            throw new ValidateException("Password must be less than 20 characters!");
        if(obj.getUsername().length()>20)
            throw new ValidateException("Username must be less than 20 characters!");

    }
}
