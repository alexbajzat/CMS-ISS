package com.frasinu.iss.persistance.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class ProgramCommitteeMemberBuilder {
    private Integer id;
    private String affiliation;
    private String email;
    private String webpage;

    ProgramCommitteeMemberBuilder() {
    }

    public ProgramCommitteeMember build() {
        return new ProgramCommitteeMember(id, affiliation, email, webpage);
    }

    public ProgramCommitteeMemberBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ProgramCommitteeMemberBuilder setAffiliation(String affiliation) {
        this.affiliation = affiliation;
        return this;
    }

    public ProgramCommitteeMemberBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ProgramCommitteeMemberBuilder setWebpage(String webpage) {
        this.webpage = webpage;
        return this;
    }
}
