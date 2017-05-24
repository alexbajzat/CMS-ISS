package com.frasinu.iss.service.service_requests.reviewer;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateReviewerRequest {
    private String affiliation;
    private String email;
    private String webpage;
    private User user;
    private ConferenceEdition conferenceEdition;

    public CreateReviewerRequest(String affiliation, String email,String webpage, User user, ConferenceEdition conferenceEdition) {
        this.affiliation = affiliation;
        this.email = email;
        this.user = user;
        this.conferenceEdition=conferenceEdition;
        this.webpage=webpage;
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

    public String getWebpage() {
        return webpage;
    }
}
