package com.frasinu.iss.service.service_requests.user;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindIfUserIsAuthorRequest {
    private final int idUser;
    private final int idEdition;

    public FindIfUserIsAuthorRequest(int idUser,int idEdition) {
        this.idEdition=idEdition;this.idUser=idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdEdition() {
        return idEdition;
    }
}
