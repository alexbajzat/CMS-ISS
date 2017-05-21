package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Keyword;
import com.frasinu.iss.persistance.model.Topic;
import com.frasinu.iss.persistance.repository.KeywordRepository;
import com.frasinu.iss.persistance.repository.ProposalRepository;
import com.frasinu.iss.persistance.repository.TopicRepository;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalRequest;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.service.service_requests.proposal.FindForAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bjz on 5/18/2017.
 */
@Service
public class ProposalService {
    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private TopicRepository topicRepository;

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
        List<String> keywords = createProposalRequest.getKeywords();
        List<String> topics = createProposalRequest.getTopics();
        proposalRepository.addProposalForAuthor(proposal.getId(), 1);

        authorsId.forEach(id -> {
            proposalRepository.addProposalForAuthor(proposal.getId(), id);
        });

        List<Keyword> parsedKeywords = keywords.stream()
                .map(Keyword::new)
                .collect(Collectors.toList());

        keywordRepository.save(parsedKeywords)
                .forEach(keyword -> keywordRepository.addKeywordForProposal(keyword.getId(), proposal.getId()));

        List<Topic> parsedTopics = topics.stream()
                .map(Topic::new)
                .collect(Collectors.toList());

        topicRepository.save(parsedTopics)
                .forEach(topic -> topicRepository.addTopicForProposal(topic.getId(), proposal.getId()));

        return proposal;

    }


    public List<Proposal> findForAuthor(FindForAuthorRequest findForAuthorRequest) {
        return proposalRepository.findAllForAuthor(findForAuthorRequest.getAuthorId());
    }
}
