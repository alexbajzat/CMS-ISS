package com.frasinu.iss.service.service_requests.conference;

/**
 * Created by cory_ on 20-May-17.
 */
public class FindConferenceEditionByConferenceRequest {
    private int conferenceId;

    public FindConferenceEditionByConferenceRequest(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getConferenceId() {
        return conferenceId;
    }
}
