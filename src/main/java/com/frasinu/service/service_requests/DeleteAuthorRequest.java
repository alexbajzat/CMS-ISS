package com.frasinu.service.service_requests;

/**
 * Created by Ericqw on 15.05.2017.
 */
public class DeleteAuthorRequest {
    private final int id;

    public DeleteAuthorRequest(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }
}
