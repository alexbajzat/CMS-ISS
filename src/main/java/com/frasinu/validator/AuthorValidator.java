package com.frasinu.validator;

import com.frasinu.model.Author;

import javax.xml.bind.ValidationException;

/**
 * Created by Ericqw on 17.05.2017.
 */
public class AuthorValidator implements Validator<Author> {
    @Override
    public void validare(Author obj) throws ValidationException {
        if(obj.getAffiliation().equals(""))
            throw new ValidationException("Author has no affiliation!");
        if(obj.getEmail().equals(""))
            throw new ValidationException("Author has no email!");
    }
}
