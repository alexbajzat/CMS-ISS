package com.frasinu.iss.persistance.model;

import java.sql.Time;
import java.time.LocalTime;

/**
 * Created by cory_ on 23-May-17.
 */
public class PresentationBuilder {

    private Integer id;
    private Time time;
    private ConferenceSession conferenceSession;
    private Author author;
    private Proposal proposal;


    public Presentation build() {
        return new Presentation(id, time,conferenceSession,author,proposal);
    }

    PresentationBuilder() {

    }

    public PresentationBuilder setId(Integer id) {
        this.id = id;
        return this;

    }

    public PresentationBuilder setTime(Time time) {
        this.time = time;
        return this;

    }

    public PresentationBuilder setConferenceSession(ConferenceSession conferenceSession) {
        this.conferenceSession = conferenceSession;
        return this;

    }

    public PresentationBuilder setAuthor(Author author) {
        this.author = author;
        return this;

    }

    public PresentationBuilder setProposal(Proposal proposal) {
        this.proposal = proposal;
        return this;
    }
}
