package com.frasinu.iss.service.service_requests.steeringcommitteemember;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindByUserAndConferenceEditionIdRequest {
    private final int idUser;
    private final int idEdition;

    public FindByUserAndConferenceEditionIdRequest(int idUser, int idEdition) {
        this.idUser = idUser;
        this.idEdition=idEdition;
    }

    public int getIdUser() {
        return idUser;
    }
    public int getIdEdition() {
        return idEdition;
    }

}
