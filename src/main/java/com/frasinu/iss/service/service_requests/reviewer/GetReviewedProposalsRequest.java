package com.frasinu.iss.service.service_requests.reviewer;

/**
 * Created by Andrei on 07-Jun-17.
 */
public class GetReviewedProposalsRequest {
    int idReviewer;

    public GetReviewedProposalsRequest(int idReviewer) {
        this.idReviewer = idReviewer;
    }

    public int getIdReviewer() {
        return idReviewer;
    }
}
