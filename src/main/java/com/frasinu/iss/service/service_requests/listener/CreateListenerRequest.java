package com.frasinu.iss.service.service_requests.listener;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.ConferenceSession;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalRequestBuilder;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateListenerRequest {
    private ConferenceSession conferenceSession;
    private User user;


    public ConferenceSession getConferenceSession() {
        return conferenceSession;
    }

    public User getUser() {
        return user;
    }

    public CreateListenerRequest(ConferenceSession conferenceSession, User user) {
        this.conferenceSession = conferenceSession;
        this.user = user;
    }
}
