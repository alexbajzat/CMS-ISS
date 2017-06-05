package com.frasinu.iss.persistance.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bjz on 5/9/2017.
 */
@Entity
@Table(name = "proposal")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "abstract")
    private String abstractPaper;
    @Column(name = "full_paper")
    private String fullPaper;


    @Fetch(FetchMode.SELECT)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "author_proposal",
            joinColumns = {@JoinColumn(name = "proposal_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "author_id", nullable = false, updatable = false)})
    private List<Author> authors;

    @OneToMany(mappedBy = "proposal")
    private List<ReviewedProposal> reviewed;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "proposal", fetch = FetchType.EAGER)
    private List<BiddedProposal> bidded;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(mappedBy = "proposals", fetch = FetchType.EAGER)
    private List<Keyword> keywords;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(mappedBy = "proposals", fetch = FetchType.EAGER)
    private List<Topic> topics;

    @OneToMany(mappedBy = "proposal")
    private List<Presentation> presentations;

    @ManyToOne
    private ConferenceEdition conferenceEdition;


    Proposal() {
    }

    public static ProposalBuilder builder() {
        return new ProposalBuilder();
    }

    Proposal(Integer id, String title, String abstractPaper, String fullPaper, List<Author> authors, List<Keyword> keywords,
             List<Topic> topics, ConferenceEdition conferenceEdition) {
        this.id = id;
        this.title = title;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
        this.authors = authors;
        this.keywords = keywords;
        this.topics = topics;
        this.conferenceEdition = conferenceEdition;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractPaper() {
        return abstractPaper;
    }

    public String getFullPaper() {
        return fullPaper;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public List<ReviewedProposal> getReviews() {
        return reviewed;
    }

    public List<BiddedProposal> getBids() {
        return bidded;
    }

    public ConferenceEdition getConferenceEdition() {
        return conferenceEdition;
    }

    public List<ReviewedProposal> getReviewed() {
        return reviewed;
    }

    public List<BiddedProposal> getBidded() {
        return bidded;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }
}

