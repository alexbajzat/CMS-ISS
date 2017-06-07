package com.frasinu.iss.persistance.model;

/**
 * Created by cory_ on 07-Jun-17.
 */
public class ListenerBuilder {
    private int id;
    private User user;
    private ConferenceSession conferenceSession;

    public Listener build() {
        return new Listener(id, user,conferenceSession);
    }

    ListenerBuilder() {

    }

    public ListenerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ListenerBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public ListenerBuilder setConferenceSession(ConferenceSession conferenceSession) {
        this.conferenceSession = conferenceSession;
        return this;
    }
}
