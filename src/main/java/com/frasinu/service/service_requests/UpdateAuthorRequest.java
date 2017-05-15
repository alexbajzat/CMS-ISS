package com.frasinu.service.service_requests;

/**
 * Created by Ericqw on 15.05.2017.
 */
public class UpdateAuthorRequest {
    private final int idOfAuthorToUpdate;
    private final String newEmail;
    private final String newAffiliation;

    public UpdateAuthorRequest(int idOfAuthorToUpdate,String newEmail, String newAffiliation){
        this.idOfAuthorToUpdate=idOfAuthorToUpdate;
        this.newAffiliation=newAffiliation;
        this.newEmail=newEmail;
    }

    public int getIdOfAuthorToUpdate() {
        return idOfAuthorToUpdate;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public String getNewAffiliation() {
        return newAffiliation;
    }
}
