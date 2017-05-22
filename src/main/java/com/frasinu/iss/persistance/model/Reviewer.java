package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bjz on 5/21/2017.
 */
@Entity
@Table(name = "reviewer")
public class Reviewer {
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "affiliation")
    private String affiliation;
    @Column(name = "email")
    private String email;
    @Column(name = "webpage")
    private String webpage;

    @ManyToOne()
    @JoinColumn(name = "id_user", nullable = false)
    private User user;


    @OneToMany(mappedBy = "reviewer")
    private List<ReviewedProposal> proposals;

    public Reviewer() {
    }

    Reviewer(Integer id, String affiliation, String email, String webpage) {
        this.id = id;
        this.affiliation = affiliation;
        this.email = email;
        this.webpage = webpage;
    }

    public Integer getId() {
        return id;
    }

    public List<ReviewedProposal> getProposals() {
        return proposals;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }

    public User getUser() {
        return user;
    }

    public String getWebpage() {
        return webpage;
    }
}
