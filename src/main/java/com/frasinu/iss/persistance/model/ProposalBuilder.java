package com.frasinu.iss.persistance.model;

import java.util.List;

/**
 * Created by bjz on 5/9/2017.
 */
public class ProposalBuilder {
    private Integer id;
    private String title;
    private String abstractPaper;
    private String fullPaper;
    private List<Author> authors;
    private List<Keyword> keywords;
    private List<Topic> topics;

    public Proposal build() {
        return new Proposal(id, title, abstractPaper, fullPaper, authors, keywords, topics);
    }

    ProposalBuilder() {
    }

    public ProposalBuilder setTopics(List<Topic> topics) {
        this.topics = topics;
        return this;
    }

    public ProposalBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ProposalBuilder setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
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

    public ProposalBuilder setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }
}

