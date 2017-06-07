package com.frasinu.iss.service.service_requests.proposal;

/**
 * Created by Andrei on 08-Jun-17.
 */
public class GetAllReviewedByProposalIdRequest {
    private int idPaper;

    public int getIdPaper() {
        return idPaper;
    }

    public GetAllReviewedByProposalIdRequest(int idPaper) {

        this.idPaper = idPaper;
    }
}
