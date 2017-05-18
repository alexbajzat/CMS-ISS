package com.frasinu.model;

import javax.persistence.*;

/**
 * Created by bjz on 5/9/2017.
 */
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name ="affiliation")
    private String affiliation;
    @Column(name ="email")
    private String email;

    Author() {
    }

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
