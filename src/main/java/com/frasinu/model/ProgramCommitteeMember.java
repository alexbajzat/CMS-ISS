package com.frasinu.model;

import javax.persistence.*;

/**
 * Created by bjz on 5/9/2017.
 */
@Entity
@Table(name = "program_committee_member")
public class ProgramCommitteeMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer id;
    @Column(name = "affiliation")
    private final String affiliation;
    @Column(name = "email")
    private final String email;
    @Column(name = "webpage")
    private final String webpage;

    public ProgramCommitteeMember() {
        id=0;
        affiliation="";
        email="";
        webpage="";
    }

    public static ProgramCommitteeMemberBuilder builder() {
        return new ProgramCommitteeMemberBuilder();
    }

    ProgramCommitteeMember(Integer id, String affiliation, String email, String webpage) {
        this.id = id;
        this.affiliation = affiliation;
        this.email = email;
        this.webpage = webpage;
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

    public String getWebpage() {
        return webpage;
    }

    @Override
    public String toString() {
        return "ProgramCommitteeMember{" +
                "id=" + id +
                ", affiliation='" + affiliation + '\'' +
                ", email='" + email + '\'' +
                ", webpage='" + webpage + '\'' +
                '}';
    }
}
