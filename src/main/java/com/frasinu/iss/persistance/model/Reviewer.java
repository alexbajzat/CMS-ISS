package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bjz on 5/21/2017.
 */
@Entity
@Table(name = "reviewer")
public class Reviewer {
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "affiliation")
    private String affiliation;
    @Column(name = "email")
    private String email;
    @Column(name = "webpage")
    private String webpage;

    @ManyToOne()
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "id_conference_edition", nullable = false)
    private ConferenceEdition conferenceEdition;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "reviewer")
    private List<ReviewedProposal> reviewedProposals;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "reviewer")
    protected List<BiddedProposal> biddedProposals;

    public Reviewer() {
    }

    Reviewer(Integer id, String affiliation, String email, String webpage,User user,ConferenceEdition conferenceEdition) {
        this.id = id;
        this.affiliation = affiliation;
        this.email = email;
        this.webpage = webpage;
        this.user=user;
        this.conferenceEdition=conferenceEdition;
    }

    public static ReviewerBuilder builder() {
        return new ReviewerBuilder();
    }

    public Integer getId() {
        return id;
    }

    public List<ReviewedProposal> getReviewedProposals() {
        return reviewedProposals;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }

    public User getUser() {
        return user;
    }

    public String getWebpage() {
        return webpage;
    }

    public List<BiddedProposal> getBiddedProposals() {
        return biddedProposals;
    }

    public ConferenceEdition getConferenceEdition() {
        return conferenceEdition;
    }
}
