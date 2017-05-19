package com.frasinu.service.service_requests.user;

/**
 * Created by bjz on 5/7/2017.
 */
public class DeleteUserRequest {
    private final int id;

    public DeleteUserRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
