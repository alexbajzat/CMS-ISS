package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.repository.ConferenceRepository;
import com.frasinu.iss.service.service_requests.conference.FindConferenceEditionByConferenceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cory_ on 19-May-17.
 */
@Service
public class ConferenceService{
    @Autowired
    private ConferenceRepository conferenceRepository;

    public List<Conference> getAll() {
        return conferenceRepository.findAll();
    }

    public List<ConferenceEdition> findConferenceEditionsByConference(FindConferenceEditionByConferenceRequest findConferenceEditionByConferenceRequest){
        return conferenceRepository.findByConferenceId(findConferenceEditionByConferenceRequest.getConferenceId()).getConferenceEditions();
    }
}
