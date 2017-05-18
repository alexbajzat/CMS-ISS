package com.frasinu.model;


import javax.persistence.*;

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

    Proposal() {
    }

    public static ProposalBuilder builder() {
        return new ProposalBuilder();
    }

    Proposal(Integer id, String title, String abstractPaper, String fullPaper) {
        this.id = id;
        this.title = title;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
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
}
