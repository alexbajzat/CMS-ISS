package com.frasinu.iss.service.service_requests.user;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindByIdRequest {
    private final int id;

    public FindByIdRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
