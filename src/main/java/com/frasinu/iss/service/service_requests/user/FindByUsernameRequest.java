package com.frasinu.iss.service.service_requests.user;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindByUsernameRequest {
    private final String username;

    public FindByUsernameRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
