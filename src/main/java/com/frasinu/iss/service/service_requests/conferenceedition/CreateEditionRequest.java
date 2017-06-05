package com.frasinu.iss.service.service_requests.conferenceedition;

import com.frasinu.iss.persistance.model.Conference;

import java.time.LocalDate;

/**
 * Created by Betty on 6/2/2017.
 */
public class CreateEditionRequest {
    private String name;
    private LocalDate conferenceStartDate;
    private LocalDate conferenceEndDate;
    private LocalDate abstractsDeadline;
    private LocalDate fullPapersDeadline;
    private LocalDate biddingDeadline;
    private LocalDate evaluationDeadline;

    private Conference conference;

    public CreateEditionRequest(String name, LocalDate conferenceStartDate, LocalDate conferenceEndDate, LocalDate abstractsDeadline, LocalDate fullPapersDeadline, LocalDate biddingDeadline, LocalDate evaluationDeadline, Conference conference) {
        this.name = name;
        this.conferenceStartDate = conferenceStartDate;
        this.conferenceEndDate = conferenceEndDate;
        this.abstractsDeadline = abstractsDeadline;
        this.fullPapersDeadline = fullPapersDeadline;
        this.biddingDeadline = biddingDeadline;
        this.evaluationDeadline = evaluationDeadline;
        this.conference = conference;
    }

    public String getName() {
        return name;
    }

    public LocalDate getConferenceStartDate() {
        return conferenceStartDate;
    }

    public LocalDate getConferenceEndDate() {
        return conferenceEndDate;
    }

    public LocalDate getAbstractsDeadline() {
        return abstractsDeadline;
    }

    public LocalDate getFullPapersDeadline() {
        return fullPapersDeadline;
    }

    public LocalDate getBiddingDeadline() {
        return biddingDeadline;
    }

    public LocalDate getEvaluationDeadline() {
        return evaluationDeadline;
    }

    public Conference getConference() {
        return conference;
    }
}
