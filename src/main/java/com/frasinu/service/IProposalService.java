package com.frasinu.service;

import com.frasinu.model.Proposal;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public interface IProposalService {
    List<Proposal> getAll();

    List<Proposal> findAllAccepted();

    List<Proposal> findForTopic(String topic);

    List<Proposal> findForKeywords(List<String> keywords);
}
