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

    @ManyToOne()
    @JoinColumn(name = "id_conference_edition", nullable = false)
    private ConferenceEdition conferenceEdition;

    @ManyToMany(mappedBy = "authors")
    private List<Proposal> proposals;

    @OneToMany(mappedBy = "author")
    private List<Presentation> presentations;

    Author() {
    }

    public static AuthorBuilder builder() {
        return new AuthorBuilder();
    }

    Author(Integer id, String affiliation, String email, User user,ConferenceEdition conferenceEdition) {
        this.id = id;
        this.affiliation = affiliation;
        this.email = email;
        this.user=user;
        this.conferenceEdition=conferenceEdition;
    }

    public User getUser() {
        return user;
    }

    public ConferenceEdition getConferenceEdition() {
        return conferenceEdition;
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

    public List<Proposal> getProposals() {
        return proposals;
    }
}
