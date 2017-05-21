package com.frasinu.iss.service;

import com.frasinu.iss.persistance.repository.ProposalRepository;
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
public class ProposalService {
    @Autowired
    private ProposalRepository proposalRepository;

    public List<Proposal> getAll() {
        return proposalRepository.findAll();
    }

    public Proposal createProposalForAuthors(CreateProposalRequest createProposalRequest) {
        Proposal proposal = Proposal.builder()
                .setAbstractPaper(createProposalRequest.getAbstractPaper())
                .setFullPaper(createProposalRequest.getFullPaper())
                .setTitle(createProposalRequest.getTitle())
                .build();

        proposalRepository.save(proposal);

        List<Integer> authorsId = createProposalRequest.getAuthorsId();

        authorsId.forEach(id -> {
            proposalRepository.addProposalForAuthor(proposal.getId(), id);
        });

        return proposal;

    }


    public List<Proposal> findForAuthor(FindForAuthorRequest findForAuthorRequest) {
        return proposalRepository.findAllForAuthor(findForAuthorRequest.getAuthorId());
    }
}
