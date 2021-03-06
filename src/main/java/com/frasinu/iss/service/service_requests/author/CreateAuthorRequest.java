package com.frasinu.iss.service.service_requests.author;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateAuthorRequest {
    private String affiliation;
    private String email;
    private User user;
    private ConferenceEdition conferenceEdition;

    public CreateAuthorRequest(String affiliation, String email,User user,ConferenceEdition conferenceEdition) {
        this.affiliation = affiliation;
        this.email = email;
        this.user = user;
        this.conferenceEdition=conferenceEdition;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }

    public User getUser() {
        return user;
    }

    public ConferenceEdition getConferenceEdition(){return conferenceEdition; }
}
