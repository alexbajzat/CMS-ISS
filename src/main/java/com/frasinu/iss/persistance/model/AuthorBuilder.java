package com.frasinu.iss.persistance.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class AuthorBuilder {
    private Integer id;
    private String affiliation;
    private String email;
    private User user;

    AuthorBuilder() {
    }

    public Author build() {
        return new Author(id, affiliation, email);
    }

    AuthorBuilder setId(Integer id) {
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
}
