package com.frasinu.iss.persistance.model;

import java.time.LocalDate;

/**
 * Created by cory_ on 16-May-17.
 */
public class ConferenceEditionBuilder {
    private Integer id;
    private String name;
    private LocalDate conferenceStartDate;
    private LocalDate conferenceEndDate;
    private LocalDate abstractsDeadline;
    private LocalDate fullPapersDeadline;
    private LocalDate biddingDeadline;
    private LocalDate evaluationDeadline;

    private Conference conference;

    public ConferenceEditionBuilder() {
    }

    public ConferenceEdition build() {
        return new ConferenceEdition(id, name, conferenceStartDate, conferenceEndDate, abstractsDeadline, fullPapersDeadline, biddingDeadline, evaluationDeadline, conference);
    }

    public ConferenceEditionBuilder  setId(Integer id) {
        this.id = id;
        return this;
    }

    public ConferenceEditionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ConferenceEditionBuilder setConferenceStartDate(LocalDate conferenceStartDate) {
        this.conferenceStartDate = conferenceStartDate;
        return this;
    }

    public ConferenceEditionBuilder setConferenceEndDate(LocalDate conferenceEndDate) {
        this.conferenceEndDate = conferenceEndDate;
        return this;
    }

    public ConferenceEditionBuilder setAbstractsDeadline(LocalDate abstractsDeadline) {
        this.abstractsDeadline = abstractsDeadline;
        return this;
    }

    public ConferenceEditionBuilder setFullPapersDeadline(LocalDate fullPapersDeadline) {
        this.fullPapersDeadline = fullPapersDeadline;
        return this;
    }

    public ConferenceEditionBuilder setBiddingDeadline(LocalDate biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
        return this;
    }

    public ConferenceEditionBuilder setEvaluationDeadline(LocalDate evaluationDeadline) {
        this.evaluationDeadline = evaluationDeadline;
        return this;
    }

    public ConferenceEditionBuilder setConference(Conference conference) {
        this.conference = conference;
        return this;
    }
}
