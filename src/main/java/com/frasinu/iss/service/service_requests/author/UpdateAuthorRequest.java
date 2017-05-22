package com.frasinu.iss.service.service_requests.author;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;

/**
 * Created by bjz on 5/18/2017.
 */
public class UpdateAuthorRequest {
    private int idOfAuthorToUpdate;
    private String affiliation;
    private String email;
    private User user;
    private ConferenceEdition conferenceEdition;

    public UpdateAuthorRequest(int idOfAuthorToUpdate,String affiliation, String email, User user, ConferenceEdition conferenceEdition) {
        this.idOfAuthorToUpdate=idOfAuthorToUpdate;
        this.affiliation = affiliation;
        this.email = email;
        this.user = user;
        this.conferenceEdition=conferenceEdition;
    }

    public int getIdOfAuthorToUpdate() {
        return idOfAuthorToUpdate;
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
