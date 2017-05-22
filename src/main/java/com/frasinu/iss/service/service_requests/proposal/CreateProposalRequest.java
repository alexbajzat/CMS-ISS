package com.frasinu.iss.service.service_requests.proposal;

import com.frasinu.iss.persistance.model.Keyword;
import com.frasinu.iss.persistance.model.Topic;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateProposalRequest {
    private String title;
    private String abstractPaper;
    private String fullPaper;
    private List<Integer> authorsId;
    private List<String> keywords;
    private List<String> topics;

    CreateProposalRequest(String title, String abstractPaper, String fullPaper, List<Integer> authorId,
                          List<String> keywords, List<String> topics) {
        this.title = title;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
        this.authorsId = authorId;
        this.keywords = keywords;
        this.topics = topics;
    }

    public static CreateProposalRequestBuilder builder() {
        return new CreateProposalRequestBuilder();
    }

    public List<Integer> getAuthorsId() {
        return authorsId;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractPaper() {
        return abstractPaper;
    }

    public String getFullPaper() {
        return fullPaper;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public List<String> getTopics() {
        return topics;
    }
}
