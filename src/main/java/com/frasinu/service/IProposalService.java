package com.frasinu.service;

import com.frasinu.persistance.model.Proposal;
import com.frasinu.service.service_requests.pcmember.AddPCMRequest;
import com.frasinu.service.service_requests.proposal.CreateProposalForAuthorRequest;
import com.frasinu.service.service_requests.proposal.CreateProposalRequest;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public interface IProposalService {
    List<Proposal> getAll();

    List<Proposal> findAllAccepted();

    List<Proposal> findForTopic(String topic);

    List<Proposal> findForKeywords(List<String> keywords);

    Proposal createProposal(CreateProposalRequest createProposalRequest);

    Proposal addProposalForAuthor(CreateProposalForAuthorRequest createProposalForAuthorRequest);
}
