package com.frasinu.iss.service.service_requests.reviewer;

/**
 * Created by Andrei on 24-May-17.
 */
public class AssignPaperToReviewerRequest {
    private int idReviewer;
    private int idPaper;
    private String result;

    public AssignPaperToReviewerRequest(int idReviewer, int idPaper, String result) {
        this.idReviewer = idReviewer;
        this.idPaper = idPaper;
        this.result = result;
    }

    public int getIdReviewer() {
        return idReviewer;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public String getResult() {
        return result;
    }
}
