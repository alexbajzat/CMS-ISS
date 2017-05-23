package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.ConferenceSession;
import com.frasinu.iss.persistance.repository.ConferenceEditionRepository;
import com.frasinu.iss.persistance.repository.ConferenceSessionRepository;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindConferenceByConferenceEditionIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cory_ on 20-May-17.
 */
@Service
public class ConferenceSessionService {
    @Autowired
    private ConferenceSessionRepository conferenceSessionRepository;


    public List<ConferenceSession> findByConferenceEditionId(FindByConferenceEditionIdRequest findByConferenceEditionIdRequest){
        return conferenceSessionRepository.findByConferenceEditionId(findByConferenceEditionIdRequest.getConferenceEditionId());
    }

}
