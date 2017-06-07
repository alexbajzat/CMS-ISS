package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

/**
 * Created by cory_ on 23-May-17.
 */

@Entity
@Table(name = "presentation")
public class Presentation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time")
    private Time time;


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

    public static PresentationBuilder builder() {
        return new PresentationBuilder();
    }

    Presentation(Integer id, Time time, ConferenceSession conferenceSession, Author author, Proposal proposal) {
        this.id=id;
        this.time = time;
        this.conferenceSession = conferenceSession;
        this.author = author;
        this.proposal = proposal;
    }

    public Integer getId() {
        return id;
    }

    public Time getTime() {
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
