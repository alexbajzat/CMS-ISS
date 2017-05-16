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
    private final Integer id;
    @Column(name = "affiliation")
    private final String affiliation;
    @Column(name = "email")
    private final String email;

    public Author() {
        id=0;
        affiliation="";
        email="";
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

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", affiliation='" + affiliation + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
