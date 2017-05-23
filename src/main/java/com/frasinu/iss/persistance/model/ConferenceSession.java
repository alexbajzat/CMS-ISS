package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cory_ on 19-May-17.
 */
@Entity
@Table(name = "conference_session")
public class ConferenceSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "am_pm")
    private String amPm;

    @ManyToOne
    @JoinColumn(name = "id_conference_edition")
    private ConferenceEdition conferenceEdition;



    public ConferenceSession() {
    }

    ConferenceSession(Integer id, String name, LocalDate date, String amPm,ConferenceEdition conferenceEdition){
        this.id = id;
        this.name = name;
        this.date = date;
        this.amPm = amPm;
        this.conferenceEdition = conferenceEdition;
    }

    public static ConferenceSessionBuilder builder() {
        return new ConferenceSessionBuilder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public LocalDate getDate() {
        return date;
    }

    public String getAmPm() {
        return amPm;
    }

    public ConferenceEdition getConferenceEdition() {
        return conferenceEdition;
    }
}

