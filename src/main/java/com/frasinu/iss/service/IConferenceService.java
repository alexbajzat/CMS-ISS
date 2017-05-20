package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.service_requests.conference.FindConferenceEditionByConferenceRequest;

import java.util.List;

/**
 * Created by cory_ on 19-May-17.
 */
public interface IConferenceService {
    List<Conference> getAll();
    List<ConferenceEdition> findConferenceEditionsByConference(FindConferenceEditionByConferenceRequest findConferenceEditionByConferenceRequest);
}
