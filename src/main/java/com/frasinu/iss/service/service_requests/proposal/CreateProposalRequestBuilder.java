package com.frasinu.iss.service.service_requests.proposal;

import com.frasinu.iss.persistance.model.ConferenceEdition;

import java.util.List;

/**
 * Created by bjz on 5/21/2017.
 */
public class CreateProposalRequestBuilder {
    private String title;
    private String abstractPaper;
    private String fullPaper;
    private List<Integer> authorsId;
    private List<String> keywords;
    private List<String> topics;
    private ConferenceEdition conferenceEdition;

    CreateProposalRequestBuilder() {
    }

    public CreateProposalRequest build() {
        return new CreateProposalRequest(title, abstractPaper, fullPaper, authorsId, keywords, topics, conferenceEdition);
    }

    public CreateProposalRequestBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateProposalRequestBuilder setConferenceEdition(ConferenceEdition conferenceEdition) {
        this.conferenceEdition = conferenceEdition;
        return this;
    }

    public CreateProposalRequestBuilder setAbstractPaper(String abstractPaper) {
        this.abstractPaper = abstractPaper;
        return this;
    }

    public CreateProposalRequestBuilder setFullPaper(String fullPaper) {
        this.fullPaper = fullPaper;
        return this;
    }

    public CreateProposalRequestBuilder setAuthorsId(List<Integer> authorsId) {
        this.authorsId = authorsId;
        return this;
    }

    public CreateProposalRequestBuilder setKeywords(List<String> keywords) {
        this.keywords = keywords;
        return this;
    }

    public CreateProposalRequestBuilder setTopics(List<String> topics) {
        this.topics = topics;
        return this;
    }
}
