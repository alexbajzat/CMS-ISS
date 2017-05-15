package com.frasinu.service.service_requests;



/**
 * Created by Ericqw on 15.05.2017.
 */
public class AddAuthorRequest {
    private final String affiliation;
    private final String email;

    public AddAuthorRequest(String affiliation,String email){
        this.affiliation=affiliation;
        this.email=email;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }
}
