package com.frasinu.service.service_requests.user;

/**
 * Created by bjz on 5/15/2017.
 */
public class LoginUserRequest {
    private final String username;
    private final String password;

    public LoginUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
