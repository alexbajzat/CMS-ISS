package com.frasinu.iss.service.service_requests.presentation;

import com.frasinu.iss.persistance.model.*;

import java.sql.Time;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreatePresentationRequest {
    private Time time;
    private Author author;
    private ConferenceSession conferenceSession;
    private Proposal proposal;

    public CreatePresentationRequest(Time time,Author author,ConferenceSession conferenceSession,Proposal proposal) {
        this.time=time;
        this.author=author;
        this.conferenceSession=conferenceSession;
        this.proposal=proposal;
    }

    public Time getTime() {
        return time;
    }

    public Author getAuthor() {
        return author;
    }

    public ConferenceSession getConferenceSession() {
        return conferenceSession;
    }

    public Proposal getProposal() {
        return proposal;
    }
}
