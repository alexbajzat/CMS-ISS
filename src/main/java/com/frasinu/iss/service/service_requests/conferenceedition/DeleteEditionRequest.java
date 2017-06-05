package com.frasinu.iss.service.service_requests.conferenceedition;

import com.frasinu.iss.persistance.model.ConferenceEdition;

/**
 * Created by Betty on 6/2/2017.
 */
public class DeleteEditionRequest {
    private ConferenceEdition edition;
    public DeleteEditionRequest(ConferenceEdition edition) {
        this.edition=edition;
    }
    public ConferenceEdition getEdition(){
        return edition;
    }
}
