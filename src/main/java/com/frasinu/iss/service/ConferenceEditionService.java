package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.repository.ConferenceEditionRepository;
import com.frasinu.iss.service.service_requests.conference.FindByConferenceIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindConferenceByConferenceEditionIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cory_ on 20-May-17.
 */
@Service

public class ConferenceEditionService implements IConferenceEditionService {
    @Autowired
    private ConferenceEditionRepository conferenceEditionRepository;

    @Override
    public List<ConferenceEdition> getAll() {
        return conferenceEditionRepository.findAll();
    }

    @Override
    public ConferenceEdition findByConferenceEditionId(FindByConferenceEditionIdRequest findByConferenceEditionIdRequest){
        return conferenceEditionRepository.findByConferenceEditionId(findByConferenceEditionIdRequest.getConferenceEditionId());
    }
    @Override
    public Conference findConferenceByConferenceEditionId(FindConferenceByConferenceEditionIdRequest findConferenceByConferenceEditionIdRequest){
        return conferenceEditionRepository.findByConferenceEditionId(findConferenceByConferenceEditionIdRequest.getConferenceEditionId()).getConference();
    }
}
