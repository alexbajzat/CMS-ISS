package com.frasinu.iss.persistance.model;

/**
 * Created by bjz on 5/21/2017.
 */
public class ReviewerBuilder {
    private Integer id;
    private String affiliation;
    private String email;
    private String webpage;
    private ConferenceEdition conferenceEdition;
    private User user;

    public ReviewerBuilder() {
    }

    public Reviewer build() {
        return new Reviewer(id, affiliation, email, webpage,user,conferenceEdition);
    }

    public ReviewerBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ReviewerBuilder setAffiliation(String affiliation) {
        this.affiliation = affiliation;
        return this;
    }

    public ReviewerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ReviewerBuilder setWebpage(String webpage) {
        this.webpage = webpage;
        return this;
    }
}

