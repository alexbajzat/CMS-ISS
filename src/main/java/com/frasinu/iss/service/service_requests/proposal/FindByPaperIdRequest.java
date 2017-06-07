package com.frasinu.iss.service.service_requests.proposal;

/**
 * Created by bjz on 6/5/2017.
 */
public class FindByPaperIdRequest {
    private final Integer id;

    public FindByPaperIdRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
