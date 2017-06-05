package com.frasinu.iss.service.service_requests.conference;

/**
 * Created by Betty on 6/1/2017.
 */
public class CreateConferenceRequest {
    private String name;
    private String webpage;

    public CreateConferenceRequest(String name, String webpage) {
        this.name = name;
        this.webpage = webpage;
    }

    public String getName() {
        return name;
    }

    public String getWebpage() {
        return webpage;
    }
}
