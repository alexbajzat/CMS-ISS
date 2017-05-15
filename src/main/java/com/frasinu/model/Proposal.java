package com.frasinu.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class Proposal {
    private final Integer id;
    private final String title;
    private final String abstractPaper;
    private final String fullPaper;

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
