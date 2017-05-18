package com.frasinu.exception;

/**
 * Created by bjz on 5/18/2017.
 */
public class DuplicatedValueException extends RuntimeException {
    public DuplicatedValueException(String message) {
        super(message);
    }
}
