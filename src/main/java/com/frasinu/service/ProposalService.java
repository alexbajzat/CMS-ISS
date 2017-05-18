package com.frasinu.service;

import com.frasinu.model.Proposal;
import com.frasinu.repository.ProposalRepository;
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
}
