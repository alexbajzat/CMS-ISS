package com.frasinu.iss.service.service_requests.listener;

/**
 * Created by bjz on 5/15/2017.
 */
public class FindByUserAndSessionIdRequest {
    private final int idUser;
    private final int idSession;

    public FindByUserAndSessionIdRequest(int idUser, int idSession) {
        this.idUser = idUser;
        this.idSession=idSession;
    }

    public int getIdUser() {
        return idUser;
    }
    public int getIdSession() {
        return idSession;
    }

}
