package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.service_requests.conference.FindByConferenceIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindConferenceByConferenceEditionIdRequest;

import java.util.List;

/**
 * Created by cory_ on 20-May-17.
 */
public interface IConferenceEditionService {
    List<ConferenceEdition> getAll();
    public ConferenceEdition findByConferenceEditionId(FindByConferenceEditionIdRequest findByConferenceEditionIdRequest);
    public Conference findConferenceByConferenceEditionId(FindConferenceByConferenceEditionIdRequest findConferenceByConferenceEditionIdRequest);
}
