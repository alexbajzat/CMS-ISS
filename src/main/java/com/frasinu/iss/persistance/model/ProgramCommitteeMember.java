package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bjz on 5/9/2017.
 */
@Entity
@Table(name = "program_committee_member")
public class ProgramCommitteeMember {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "affiliation")
    protected String affiliation;

    @Column(name = "email")
    protected String email;

    @Column(name = "webpage")
    protected String webpage;

    @OneToMany(mappedBy = "PCMember",fetch = FetchType.EAGER)
    protected List<BiddedProposal> biddedProposals;

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

    public List<BiddedProposal> getBiddedProposals() {
        return biddedProposals;
    }
}
