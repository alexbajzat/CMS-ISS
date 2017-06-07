package com.frasinu.iss.persistance.model;

import javax.persistence.*;

/**
 * Created by cory_ on 07-Jun-17.
 */
@Entity
@Table(name = "listener")
public class Listener {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_conference_session")
    private ConferenceSession conferenceSession;

    public Listener() {
    }

    public Listener(Integer id,User user, ConferenceSession conferenceSession) {
        this.id=id;
        this.user = user;
        this.conferenceSession = conferenceSession;
    }

    public static ListenerBuilder builder() {
        return new ListenerBuilder();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ConferenceSession getConferenceSession() {
        return conferenceSession;
    }
}
