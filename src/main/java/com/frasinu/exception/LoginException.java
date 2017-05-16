package com.frasinu.exception;

/**
 * Created by cory_ on 16-May-17.
 */
public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}