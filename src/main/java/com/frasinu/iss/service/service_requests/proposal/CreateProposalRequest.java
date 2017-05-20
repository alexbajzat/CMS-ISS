package com.frasinu.iss.service.service_requests.proposal;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateProposalRequest {
    private String title;
    private String abstractPaper;
    private String fullPaper;

    public CreateProposalRequest(String title, String abstractPaper, String fullPaper) {
        this.title = title;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
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