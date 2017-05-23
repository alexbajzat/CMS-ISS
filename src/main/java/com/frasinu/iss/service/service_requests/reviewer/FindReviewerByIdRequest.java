package com.frasinu.iss.service.service_requests.reviewer;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindReviewerByIdRequest {
    private final int id;

    public FindReviewerByIdRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
