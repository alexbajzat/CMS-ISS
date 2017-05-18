package com.frasinu.validator;

import com.frasinu.exception.ValidateException;


/**
 * Created by Toshiba on 5/8/2017.
 */
public interface Validator<T> {
    void validare(T obj) throws ValidateException;
}
