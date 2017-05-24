package com.frasinu.iss.service.service_requests.author;

/**
 * Created by bjz on 5/20/2017.
 */
public class FindAllByConferenceEditionRequest {
    private int idConferenceEdition;

    public FindAllByConferenceEditionRequest(int idConferenceEdition) {
        this.idConferenceEdition=idConferenceEdition;
    }

    public int getConferenceEditionId() {
        return idConferenceEdition;
    }
}
