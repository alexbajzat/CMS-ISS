package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cory_ on 19-May-17.
 */
@Entity
@Table(name = "conference_edition")
public class ConferenceEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "conference_start_date")
    private LocalDate conferenceStartDate;
    @Column(name = "conference_end_date")
    private LocalDate conferenceEndDate;
    @Column(name = "abstracts_deadline")
    private LocalDate abstractsDeadline;
    @Column(name = "full_papers_deadline")
    private LocalDate fullPapersDeadline;
    @Column(name = "bidding_deadline")
    private LocalDate biddingDeadline;
    @Column(name = "evaluation_ceadline")
    private LocalDate evaluationDeadline;

    @ManyToOne
    @JoinColumn(name = "id_conference")
    private Conference conference;

    @OneToMany(mappedBy = "conferenceEdition")
    private List<Author> authors;

    @OneToMany(mappedBy = "conferenceEdition")
    private List<Reviewer> reviewers;

    @OneToMany(mappedBy = "conferenceEdition")
    private List<SteeringCommitteeMember> steeringCommitteeMembers;


    public ConferenceEdition() {
    }

    ConferenceEdition(Integer id, String name, LocalDate conferenceStartDate, LocalDate conferenceEndDate
            , LocalDate abstractsDeadline, LocalDate fullPapersDeadline, LocalDate biddingDeadline, LocalDate evaluationDeadline, Conference conference) {
        this.id = id;
        this.name = name;
        this.conferenceStartDate = conferenceStartDate;
        this.conferenceEndDate = conferenceEndDate;
        this.abstractsDeadline = abstractsDeadline;
        this.fullPapersDeadline = fullPapersDeadline;
        this.biddingDeadline = biddingDeadline;
        this.evaluationDeadline = evaluationDeadline;
        this.conference = conference;
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
        return this.conference;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}

