package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "affiliation")
    private String affiliation;
    @Column(name = "email")
    private String email;

    @ManyToOne()
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Proposal> proposals;


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

    public User getUser() {
        return user;
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
