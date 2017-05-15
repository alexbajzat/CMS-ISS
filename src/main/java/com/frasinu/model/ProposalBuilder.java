package com.frasinu.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class ProposalBuilder {
    private Integer id;
    private String title;
    private String abstractPaper;
    private String fullPaper;

    public Proposal build() {
        return new Proposal(id, title, abstractPaper, fullPaper);
    }

    ProposalBuilder() {
    }

    public ProposalBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ProposalBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProposalBuilder setAbstractPaper(String abstractPaper) {
        this.abstractPaper = abstractPaper;
        return this;
    }

    public ProposalBuilder setFullPaper(String fullPaper) {
        this.fullPaper = fullPaper;
        return this;
    }
}

