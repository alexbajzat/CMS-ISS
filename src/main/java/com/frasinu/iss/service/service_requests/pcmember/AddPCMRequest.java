package com.frasinu.service.service_requests.pcmember;

/**
 * Created by bjz on 5/18/2017.
 */
public class AddPCMRequest {
    private String title;
    private String abstractPaper;
    private String fullPaper;

    public AddPCMRequest(String title, String abstractPaper, String fullPaper) {
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
