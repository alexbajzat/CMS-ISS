package com.frasinu.iss.service.service_requests.proposal;

/**
 * Created by bjz on 5/20/2017.
 */
public class FindForAuthorRequest {
    private int authorId;

    public FindForAuthorRequest(int authorId) {
        this.authorId = authorId;
    }

    public int getAuthorId() {
        return authorId;
    }
}
