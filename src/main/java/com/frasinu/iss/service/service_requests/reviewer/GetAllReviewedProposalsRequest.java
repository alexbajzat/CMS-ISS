package com.frasinu.iss.service.service_requests.reviewer;

/**
 * Created by Andrei on 07-Jun-17.
 */
public class GetAllReviewedProposalsRequest {
    int idReviewer;

    public GetAllReviewedProposalsRequest(int idReviewer) {
        this.idReviewer = idReviewer;
    }

    public int getIdReviewer() {
        return idReviewer;
    }
}
