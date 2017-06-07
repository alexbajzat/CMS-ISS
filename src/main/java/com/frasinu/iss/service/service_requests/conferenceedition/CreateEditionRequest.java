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
        return conferenceStartDate.plusDays(1);
    }

    public LocalDate getConferenceEndDate() {
        return conferenceEndDate.plusDays(1);
    }

    public LocalDate getAbstractsDeadline() {
        return abstractsDeadline.plusDays(1);
    }

    public LocalDate getFullPapersDeadline() {
        return fullPapersDeadline.plusDays(1);
    }

    public LocalDate getBiddingDeadline() {
        return biddingDeadline.plusDays(1);
    }

    public LocalDate getEvaluationDeadline() {
        return evaluationDeadline.plusDays(1);
    }

    public Conference getConference() {
        return conference;
    }
}
