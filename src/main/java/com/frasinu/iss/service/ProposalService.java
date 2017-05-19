package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Keyword;
import com.frasinu.iss.persistance.repository.ProposalRepository;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalForAuthorRequest;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalRequest;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.service.service_requests.proposal.FindForAuthorRequest;
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

    @Override
    public List<Proposal> findForAuthor(FindForAuthorRequest findForAuthorRequest) {
        return proposalRepository.findAllForAuthor(findForAuthorRequest.getAuthorId());
    }
}
