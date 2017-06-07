package com.frasinu.iss.persistance.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class AuthorBuilder {
    private Integer id;
    private String affiliation;
    private String email;
    private User user;
    private ConferenceEdition conferenceEdition;

    AuthorBuilder() {
    }

    public Author build() {

        return new Author(id, affiliation, email,user,conferenceEdition);
    }

    public AuthorBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public AuthorBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public AuthorBuilder setAffiliation(String affiliation) {
        this.affiliation = affiliation;
        return this;
    }

    public AuthorBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public AuthorBuilder setConferenceEdition(ConferenceEdition conferenceEdition) {
        this.conferenceEdition=conferenceEdition;
        return this;
    }

}
