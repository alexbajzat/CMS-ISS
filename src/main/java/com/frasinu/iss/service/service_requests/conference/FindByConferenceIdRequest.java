package com.frasinu.iss.service.service_requests.conference;

/**
 * Created by cory_ on 20-May-17.
 */
public class FindByConferenceIdRequest {
    private int conferenceId;

    public FindByConferenceIdRequest(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getConferenceId() {
        return conferenceId;
    }
}
