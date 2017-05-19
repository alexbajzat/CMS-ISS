package com.frasinu.iss.service.service_requests.author;

import com.frasinu.iss.persistance.model.User;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateAuthorRequest {
    private String affiliation;
    private String email;
    private User user;

    public CreateAuthorRequest(String affiliation, String email,User user) {
        this.affiliation = affiliation;
        this.email = email;
        this.user = user;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }

    public User getIdUser() {
        return user;
    }
}
