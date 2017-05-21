package com.frasinu.iss.persistance.model;

import javax.persistence.*;

/**
 * Created by Andrei on 21-May-17.
 */

enum BidResult{
    ACCEPTED, REJECTED
}

@Entity
@Table(name = "bidded_proposal")
public class BiddedProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_member_id")
    private ProgramCommitteeMember PCMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @Enumerated(EnumType.STRING)
    private BidResult result;

    BiddedProposal(){}

    public int getId() {
        return id;
    }

    public ProgramCommitteeMember getPCMember() {
        return PCMember;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public BidResult getResult() {
        return result;
    }
}
