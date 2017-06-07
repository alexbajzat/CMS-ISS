package com.frasinu.iss.service.service_requests.reviewer;

/**
 * Created by Andrei on 08-Jun-17.
 */
public class GetReviewedProposalRequest {
    private int idReviewer;
    private int idPaper;

    public GetReviewedProposalRequest(int idReviewer, int idPaper) {
        this.idReviewer = idReviewer;
        this.idPaper = idPaper;
    }

    public int getIdReviewer() {
        return idReviewer;
    }

    public int getIdPaper() {
        return idPaper;
    }
}
