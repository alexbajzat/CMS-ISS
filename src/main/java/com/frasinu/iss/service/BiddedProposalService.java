package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Bid;
import com.frasinu.iss.persistance.model.BiddedProposal;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.repository.BiddedProposalRepository;
import com.frasinu.iss.service.service_requests.biddedproposal.AddBiddedProposalRequest;
import com.frasinu.iss.service.service_requests.reviewer.GetBiddedProposalsForReviewerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjz on 6/5/2017.
 */
@Service
public class BiddedProposalService {
    @Autowired
    private BiddedProposalRepository biddedProposalRepository;

    public BiddedProposal add(AddBiddedProposalRequest addBiddedProposalRequest) {
        Proposal proposal = addBiddedProposalRequest.getProposal();
        Reviewer reviewer = addBiddedProposalRequest.getReviewer();
        Bid bid = addBiddedProposalRequest.getBid();

        BiddedProposal biddedProposal = new BiddedProposal(null, reviewer, proposal, bid.getName());

        return biddedProposalRepository.save(biddedProposal);
    }

    public List<BiddedProposal> getAllByReviewer(GetBiddedProposalsForReviewerRequest getBiddedProposalsForReviewerRequest){
        int idEditon = getBiddedProposalsForReviewerRequest.getIdEdition();
        int idReviewer = getBiddedProposalsForReviewerRequest.getIdReviewer();

        return biddedProposalRepository.findAllByReviewerAndEdition(idReviewer, idEditon);
    }
}
