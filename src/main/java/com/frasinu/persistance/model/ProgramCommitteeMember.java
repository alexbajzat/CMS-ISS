package com.frasinu.persistance.model;

import javax.persistence.*;

/**
 * Created by bjz on 5/9/2017.
 */
@Entity
@Table(name = "program_committee_member")
public class ProgramCommitteeMember {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "affiliation")
    private String affiliation;
    @Column(name = "email")
    private String email;
    @Column(name = "webpage")
    private String webpage;

    public static ProgramCommitteeMemberBuilder builder() {
        return new ProgramCommitteeMemberBuilder();
    }

    public ProgramCommitteeMember() {
    }

    ProgramCommitteeMember(Integer id, String affiliation, String email, String webpage) {
        this.id = id;
        this.affiliation = affiliation;
        this.email = email;
        this.webpage = webpage;
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
}
