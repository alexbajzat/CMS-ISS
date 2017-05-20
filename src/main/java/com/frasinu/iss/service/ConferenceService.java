package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.repository.ConferenceRepository;
import com.frasinu.iss.service.service_requests.conference.FindByConferenceIdRequest;
import com.frasinu.iss.service.service_requests.conference.FindConferenceEditionsByConferenceIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cory_ on 19-May-17.
 */
@Service
public class ConferenceService implements IConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public List<Conference> getAll() {
        return conferenceRepository.findAll();
    }

    @Override
    public List<ConferenceEdition> findConferenceEditionsByConference(FindConferenceEditionsByConferenceIdRequest findConferenceEditionByConferenceRequest){
        return conferenceRepository.findByConferenceId(findConferenceEditionByConferenceRequest.getConferenceId()).getConferenceEditions();
    }

    public Conference findByConferenceId(FindByConferenceIdRequest findByConferenceIdRequest){
        return conferenceRepository.findByConferenceId(findByConferenceIdRequest.getConferenceId());
    }
}
