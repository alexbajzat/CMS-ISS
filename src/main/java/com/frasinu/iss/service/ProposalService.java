package com.frasinu.service;

import com.frasinu.persistance.model.Proposal;
import com.frasinu.persistance.repository.ProposalRepository;
import com.frasinu.service.service_requests.pcmember.AddPCMRequest;
import com.frasinu.service.service_requests.proposal.CreateProposalForAuthorRequest;
import com.frasinu.service.service_requests.proposal.CreateProposalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Service
public class ProposalService implements IProposalService {
    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public List<Proposal> getAll() {
        return proposalRepository.findAll();
    }

    @Override
    public List<Proposal> findAllAccepted() {
        return null;
    }

    @Override
    public List<Proposal> findForTopic(String topic) {
        return null;
    }

    @Override
    public List<Proposal> findForKeywords(List<String> keywords) {
        return null;
    }

    @Override
    public Proposal createProposal(CreateProposalRequest createProposalRequest) {
        Proposal proposal = Proposal.builder()
                .setAbstractPaper(createProposalRequest.getAbstractPaper())
                .setFullPaper(createProposalRequest.getFullPaper())
                .setTitle(createProposalRequest.getTitle())
                .build();

        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal addProposalForAuthor(CreateProposalForAuthorRequest createProposalForAuthorRequest) {
        Integer idAuthor = createProposalForAuthorRequest.getIdAuthor();
        Integer idProposal = createProposalForAuthorRequest.getIdAuthor();
        //proposalRepository.addProposalForAuthor(idProposal, idAuthor);
        return proposalRepository.findOne(idProposal);
    }
}
