package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by cory_ on 23-May-17.
 */

@Entity
@Table(name = "presentation")
public class Presentation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;
    @Column(name = "time")
    private LocalTime time;


    @ManyToOne
    @JoinColumn(name = "id_conference_session")
    private ConferenceSession conferenceSession;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;


    @ManyToOne
    @JoinColumn(name = "id_proposal")
    private Proposal proposal;

    public Presentation() {
    }

    public Presentation(int id,LocalTime time, ConferenceSession conferenceSession, Author author, Proposal proposal) {
        this.id=id;
        this.time = time;
        this.conferenceSession = conferenceSession;
        this.author = author;
        this.proposal = proposal;
    }

    public int getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public ConferenceSession getConferenceSession() {
        return conferenceSession;
    }

    public Author getAuthor() {
        return author;
    }

    public Proposal getProposal() {
        return proposal;
    }
}
