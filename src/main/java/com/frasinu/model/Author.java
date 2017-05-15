package com.frasinu.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class Author {
    private final Integer id;
    private final String affiliation;
    private final String email;


    public static AuthorBuilder builder() {
        return new AuthorBuilder();
    }

    Author(Integer id, String affiliation, String email) {
        this.id = id;
        this.affiliation = affiliation;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }
}
