package com.frasinu.iss.service.service_requests.reviewer;

/**
 * Created by Andrei on 06-Jun-17.
 */
public class GetBiddedProposalsForReviewerRequest {
    private int idEdition;
    private int idReviewer;

    public GetBiddedProposalsForReviewerRequest(int idReviewer, int idEdition) {
        this.idEdition = idEdition;
        this.idReviewer = idReviewer;
    }

    public int getIdEdition() {
        return idEdition;
    }

    public int getIdReviewer() {
        return idReviewer;
    }
}
