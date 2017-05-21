package com.frasinu.iss.service.service_requests.proposal;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateProposalRequest {
    private String title;
    private String abstractPaper;
    private String fullPaper;
    private List<Integer> authorsId;

    public CreateProposalRequest(String title, String abstractPaper, String fullPaper, List<Integer> authorId) {
        this.title = title;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
        this.authorsId = authorId;
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
}
