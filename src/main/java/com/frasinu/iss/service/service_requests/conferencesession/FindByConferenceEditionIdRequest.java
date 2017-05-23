package com.frasinu.iss.service.service_requests.conferencesession;

/**
 * Created by cory_ on 20-May-17.
 */
public class FindByConferenceEditionIdRequest {
    private int conferenceEditionId;

    public FindByConferenceEditionIdRequest(int conferenceEditionId) {
        this.conferenceEditionId = conferenceEditionId;
    }

    public int getConferenceEditionId() {
        return conferenceEditionId;
    }
}
