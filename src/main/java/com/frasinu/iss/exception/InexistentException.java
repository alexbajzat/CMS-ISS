package com.frasinu.iss.exception;

/**
 * Created by bjz on 5/7/2017.
 */
public class InexistentException extends RuntimeException {
    public InexistentException(String message) {
        super(message);
    }
}
