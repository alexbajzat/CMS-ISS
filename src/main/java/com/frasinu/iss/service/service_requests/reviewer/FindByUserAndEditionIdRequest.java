package com.frasinu.iss.service.service_requests.reviewer;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindByUserAndEditionIdRequest {
    private final int idUser;
    private final int idEdition;

    public FindByUserAndEditionIdRequest(int idUser, int idEdition) {
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
