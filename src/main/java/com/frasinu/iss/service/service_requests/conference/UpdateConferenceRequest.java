package com.frasinu.iss.service.service_requests.conference;

/**
 * Created by Betty on 6/1/2017.
 */
public class UpdateConferenceRequest {
    private int id;
    private String name;
    private String webpage;

    public UpdateConferenceRequest(int id,String name, String webpage) {
        this.id=id;
        this.name = name;
        this.webpage = webpage;
    }

    public String getName() {
        return name;
    }

    public String getWebpage() {
        return webpage;
    }
    public int getId(){return id;}

}
