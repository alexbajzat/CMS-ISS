package com.frasinu.iss.service.service_requests.steeringcommitteemember;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindSteeringCommitteeMemberByIdRequest {
    private final int id;

    public FindSteeringCommitteeMemberByIdRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
