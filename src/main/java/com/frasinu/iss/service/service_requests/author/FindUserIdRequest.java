package com.frasinu.iss.service.service_requests.author;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindUserIdRequest {
    private final int id;

    public FindUserIdRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
