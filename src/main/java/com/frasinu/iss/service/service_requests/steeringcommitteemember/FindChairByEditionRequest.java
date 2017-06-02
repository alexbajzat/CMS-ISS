package com.frasinu.iss.service.service_requests.steeringcommitteemember;

import com.frasinu.iss.persistance.model.ConferenceEdition;

/**
 * Created by Betty on 6/2/2017.
 */
public class FindChairByEditionRequest {
    private ConferenceEdition ed;
    public FindChairByEditionRequest(ConferenceEdition ed) {
        this.ed=ed;
    }
    public ConferenceEdition getEdition(){return ed;}
}
