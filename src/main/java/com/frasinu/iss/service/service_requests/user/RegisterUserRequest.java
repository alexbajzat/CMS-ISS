package com.frasinu.service.service_requests.user;

/**
 * Created by bjz on 5/7/2017.
 */
public class RegisterUserRequest {
    private final String name;
    private final String username;
    private final String password;

    public RegisterUserRequest(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
