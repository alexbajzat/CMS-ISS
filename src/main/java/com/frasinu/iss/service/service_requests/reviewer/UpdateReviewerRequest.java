package com.frasinu.iss.service.service_requests.reviewer;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;

/**
 * Created by bjz on 5/18/2017.
 */
public class UpdateReviewerRequest {
    private int idOfReviewerToUpdate;
    private String affiliation;
    private String email;
    private String webpage;
    private User user;
    private ConferenceEdition conferenceEdition;

    public UpdateReviewerRequest(int idOfReviewerToUpdate, String affiliation, String email,String webpage, User user, ConferenceEdition conferenceEdition) {
        this.idOfReviewerToUpdate=idOfReviewerToUpdate;
        this.affiliation = affiliation;
        this.email = email;
        this.webpage=webpage;
        this.user = user;
        this.conferenceEdition=conferenceEdition;
    }

    public int getIdOfReviewerToUpdate() {
        return idOfReviewerToUpdate;
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
