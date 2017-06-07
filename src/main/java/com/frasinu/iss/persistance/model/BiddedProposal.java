package com.frasinu.iss.persistance.model;

import javax.persistence.*;

/**
 * Created by Andrei on 21-May-17.
 */


@Entity
@Table(name = "bidded_proposal")
public class BiddedProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @Column(name = "result")
    private String result;

    BiddedProposal(){}

    public BiddedProposal(Integer id, Reviewer reviewer, Proposal proposal, String result) {
        this.id = id;
        this.reviewer = reviewer;
        this.proposal = proposal;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public Reviewer getPCMember() {
        return reviewer;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public String getResult() {
        return result;
    }
}
