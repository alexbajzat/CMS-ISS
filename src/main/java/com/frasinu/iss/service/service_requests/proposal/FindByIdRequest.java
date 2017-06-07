package com.frasinu.iss.service.service_requests.proposal;

/**
 * Created by bjz on 6/5/2017.
 */
public class FindByIdRequest {
    private final Integer id;

    public FindByIdRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
