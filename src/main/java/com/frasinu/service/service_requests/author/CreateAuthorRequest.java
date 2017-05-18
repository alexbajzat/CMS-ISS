package com.frasinu.service.service_requests.author;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateAuthorRequest {
    private String affiliation;
    private String email;
    private Integer idUser;

    public CreateAuthorRequest(String affiliation, String email,Integer idUser) {
        this.affiliation = affiliation;
        this.email = email;
        this.idUser = idUser;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdUser() {
        return idUser;
    }
}
