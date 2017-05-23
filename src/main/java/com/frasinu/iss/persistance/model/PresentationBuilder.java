package com.frasinu.iss.persistance.model;

import java.time.LocalTime;

/**
 * Created by cory_ on 23-May-17.
 */
public class PresentationBuilder {

    private Integer id;
    private LocalTime time;
    private ConferenceSession conferenceSession;
    private Author author;
    private Proposal proposal;


    public Presentation build() {
        return new Presentation(id, time,conferenceSession,author,proposal);
    }

    PresentationBuilder() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setConferenceSession(ConferenceSession conferenceSession) {
        this.conferenceSession = conferenceSession;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}
