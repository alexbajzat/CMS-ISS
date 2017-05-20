package com.frasinu.iss.service.service_requests.conferenceedition;

/**
 * Created by cory_ on 20-May-17.
 */
public class FindConferenceByConferenceEditionIdRequest {
    private int conferenceEditionId;

    public FindConferenceByConferenceEditionIdRequest(int conferenceEditionId) {
        this.conferenceEditionId = conferenceEditionId;
    }

    public int getConferenceEditionId() {
        return conferenceEditionId;
    }
}
