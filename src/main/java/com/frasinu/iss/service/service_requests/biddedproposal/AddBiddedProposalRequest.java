package com.frasinu.iss.service.service_requests.biddedproposal;

import com.frasinu.iss.persistance.model.Bid;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.persistance.model.Reviewer;

/**
 * Created by bjz on 6/5/2017.
 */
public class AddBiddedProposalRequest {
    private final Reviewer reviewer;
    private final Proposal proposal;
    private final Bid bid;

    public AddBiddedProposalRequest(Reviewer reviewer, Proposal proposal, Bid bid) {
        this.reviewer = reviewer;
        this.proposal = proposal;
        this.bid = bid;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public Bid getBid() {
        return bid;
    }
}
