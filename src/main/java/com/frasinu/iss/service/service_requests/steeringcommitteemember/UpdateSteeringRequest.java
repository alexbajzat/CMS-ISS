package com.frasinu.iss.service.service_requests.steeringcommitteemember;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;

/**
 * Created by Betty on 5/25/2017.
 */
public class UpdateSteeringRequest {
    private int id;
    private String rank;
    private ConferenceEdition conferenceEdition;
    private User user;

    public UpdateSteeringRequest(int id,String rank, ConferenceEdition conferenceEdition, User user) {
        this.id=id;
        this.rank = rank;
        this.conferenceEdition = conferenceEdition;
        this.user = user;
    }

    public int getId(){return id;}
    public String getRank() {
        return rank;
    }

    public ConferenceEdition getConferenceEdition() {
        return conferenceEdition;
    }

    public User getUser() {
        return user;
    }
}
