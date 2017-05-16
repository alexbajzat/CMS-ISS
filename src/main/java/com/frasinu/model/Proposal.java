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
    private final Integer id;
    @Column(name = "title")
    private final String title;
    @Column(name = "abstract")
    private final String abstractPaper;
    @Column(name = "full_paper")
    private final String fullPaper;

    public Proposal() {
        this.id=0;
        this.title="";
        this.abstractPaper="";
        this.fullPaper="";
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

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", abstractPaper='" + abstractPaper + '\'' +
                ", fullPaper='" + fullPaper + '\'' +
                '}';
    }
}
