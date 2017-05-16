package com.frasinu.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by cory_ on 16-May-17.
 */
@Entity
@Table(name = "conference_edition")
public class ConferenceEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer id;
    @Column(name = "name")
    private final String name;
    @Column(name = "conference_start_date")
    private final Date conferenceStartDate;
    @Column(name = "conference_end_date")
    private final Date conferenceEndDate;
    @Column(name = "abstracts_deadline")
    private final Date abstractsDeadline;
    @Column(name = "full_papers_deadline")
    private final Date fullPapersDeadline;
    @Column(name = "bidding_deadline")
    private final Date biddingDeadline;
    @Column(name = "evaluation_deadline")
    private final Date evaluationDeadline;

    public ConferenceEdition() {
        this.id = 0;
        this.name = "";
        this.conferenceStartDate = new Date(2017,01,01);
        this.conferenceEndDate = new Date(2017,01,01);
        this.abstractsDeadline = new Date(2017,01,01);
        this.fullPapersDeadline=new Date(2017,01,01);
        this.biddingDeadline=new Date(2017,01,01);
        this.evaluationDeadline=new Date(2017,01,01);

    }

    ConferenceEdition(Integer id, String name, Date conferenceStartDate,Date conferenceEndDate, Date abstractsDeadline, Date fullPapersDeadline, Date biddingDeadline,Date evaluationDeadline) {
        this.id = id;
        this.name = name;
        this.conferenceStartDate=conferenceStartDate;
        this.conferenceEndDate=conferenceEndDate;
        this.abstractsDeadline=abstractsDeadline;
        this.fullPapersDeadline=fullPapersDeadline;
        this.biddingDeadline=biddingDeadline;
        this.evaluationDeadline=evaluationDeadline;
    }

    public static ConferenceEditionBuilder builder() {
        return new ConferenceEditionBuilder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getConferenceStartDate() {
        return conferenceStartDate;
    }

    public Date getConferenceEndDate() {
        return conferenceEndDate;
    }

    public Date getAbstractsDeadline() {
        return abstractsDeadline;
    }

    public Date getFullPapersDeadline() {
        return fullPapersDeadline;
    }

    public Date getBiddingDeadline() {
        return biddingDeadline;
    }

    public Date getEvaluationDeadline() {
        return evaluationDeadline;
    }

    @Override
    public String toString() {
        return "ConferenceEdition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conferenceStartDate=" + conferenceStartDate +
                ", conferenceEndDate=" + conferenceEndDate +
                ", abstractsDeadline=" + abstractsDeadline +
                ", fullPapersDeadline=" + fullPapersDeadline +
                ", biddingDeadline=" + biddingDeadline +
                ", evaluationDeadline=" + evaluationDeadline +
                '}';
    }
}
