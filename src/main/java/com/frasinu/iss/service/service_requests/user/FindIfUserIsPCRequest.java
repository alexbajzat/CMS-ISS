package com.frasinu.iss.service.service_requests.user;

/**
 * Created by Betty on 6/7/2017.
 */
public class FindIfUserIsPCRequest {
    private final int idUser;
    private final int idEdition;

    public FindIfUserIsPCRequest(int idUser,int idEdition) {
        this.idEdition=idEdition;this.idUser=idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdEdition() {
        return idEdition;
    }
}
