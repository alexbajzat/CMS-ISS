package com.frasinu.iss.service.service_requests.presentation;

/**
 * Created by bjz on 6/5/2017.
 */
public class FindByConferenceSessionRequest {
    private final Integer id;

    public FindByConferenceSessionRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
