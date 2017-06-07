package com.frasinu.iss.service;

import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.repository.ConferenceRepository;
import com.frasinu.iss.service.service_requests.conference.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cory_ on 19-May-17.
 */
@Service
public class ConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepository;

    public List<Conference> getAll() {
        return conferenceRepository.findAll();
    }

    public List<ConferenceEdition> findConferenceEditionsByConference(FindConferenceEditionsByConferenceIdRequest findConferenceEditionByConferenceRequest) {
        return conferenceRepository.findByConferenceId(findConferenceEditionByConferenceRequest.getConferenceId()).getConferenceEditions();
    }

    public Conference addConference(CreateConferenceRequest createConferenceRequest) {
        Conference conf = Conference.builder()
                .setName(createConferenceRequest.getName())
                .setWebpage(createConferenceRequest.getWebpage())
                .build();

        return conferenceRepository.save(conf);
    }

    public Conference updateConference(UpdateConferenceRequest updateConferenceRequest) {
        Conference conf= Conference.builder()
                .setId(updateConferenceRequest.getId())
                .setName(updateConferenceRequest.getName())
                .setWebpage(updateConferenceRequest.getWebpage())
                .build();


        if (conferenceRepository.findOne(updateConferenceRequest.getId()) == null) {
            throw new InexistentException("No such conference!");
        }


        return conferenceRepository.save(conf);
    }
    public void deleteConference(DeleteConferenceRequest deleteConferenceRequest){
        conferenceRepository.delete(deleteConferenceRequest.getId());
    }

    public Conference getConferenceById(ConferenceByIdRequest conferenceByIdRequest) {
        return conferenceRepository.findByConferenceId(conferenceByIdRequest.getId());
    }
}
