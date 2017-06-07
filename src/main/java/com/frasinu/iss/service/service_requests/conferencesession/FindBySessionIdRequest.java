package com.frasinu.iss.service.service_requests.conferencesession;

/**
 * Created by bjz on 6/5/2017.
 */
public class FindBySessionIdRequest {
    private final Integer id;

    public FindBySessionIdRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
