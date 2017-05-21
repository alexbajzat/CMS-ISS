package com.frasinu.iss.service.service_requests.conference;

/**
 * Created by cory_ on 20-May-17.
 */
public class FindConferenceEditionsByConferenceIdRequest {
    private int conferenceId;

    public FindConferenceEditionsByConferenceIdRequest(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getConferenceId() {
        return conferenceId;
    }
}
