package com.frasinu.iss.service.service_requests.reviewer;

/**
 * Created by Andrei on 24-May-17.
 */
public class FindReviewersByEditionRequest {
    private final int idEdition;

    public FindReviewersByEditionRequest( int idEdition) {
        this.idEdition=idEdition;
    }

    public int getIdEdition() {
        return idEdition;
    }
}
