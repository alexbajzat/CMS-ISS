package com.frasinu.iss.persistance.model;

import java.time.LocalDate;

/**
 * Created by cory_ on 16-May-17.
 */
public class ConferenceSessionBuilder {
    private Integer id;
    private String name;
    private LocalDate date;
    private String periodOfDay;
    private ConferenceEdition conferenceEdition;

    public ConferenceSessionBuilder() {
    }

    public ConferenceSession build() {
        return new ConferenceSession(id, name, date,periodOfDay, conferenceEdition);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConferenceSessionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ConferenceSessionBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ConferenceSessionBuilder setPeriodOfDay(String periodOfDay) {
        this.periodOfDay=periodOfDay;
        return this;

    }

    public ConferenceSessionBuilder setConferenceEdition(ConferenceEdition conferenceEdition) {
        this.conferenceEdition = conferenceEdition;
        return this;

    }
}
