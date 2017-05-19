package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalForAuthorRequest;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalRequest;
import com.frasinu.iss.service.service_requests.proposal.FindForAuthorRequest;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public interface IProposalService {
    List<Proposal> getAll();

    Proposal createProposal(CreateProposalRequest createProposalRequest);

    Proposal addProposalForAuthor(CreateProposalForAuthorRequest createProposalForAuthorRequest);

    List<Proposal> findForAuthor(FindForAuthorRequest findForAuthorRequest);
}
