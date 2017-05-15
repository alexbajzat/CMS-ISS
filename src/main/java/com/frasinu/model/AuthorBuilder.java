package com.frasinu.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class AuthorBuilder {
    private Integer id;
    private String affiliation;
    private String email;

    AuthorBuilder() {
    }

    public Author build() {
        return new Author(id, affiliation, email);
    }

    public AuthorBuilder setId(Integer id) {
        this.id = id;
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
