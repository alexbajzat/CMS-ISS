package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.repository.ConferenceEditionRepository;
import com.frasinu.iss.service.service_requests.conferenceedition.CreateEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.DeleteEditionRequest;
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
        return conferenceEditionRepository.findOne(findByConferenceEditionIdRequest.getConferenceEditionId());
    }
    @Override
    public Conference findConferenceByConferenceEditionId(FindConferenceByConferenceEditionIdRequest findConferenceByConferenceEditionIdRequest){
        return conferenceEditionRepository.findOne(findConferenceByConferenceEditionIdRequest.getConferenceEditionId()).getConference();
    }
    public List<ConferenceEdition> findEditionsFotConference(int idConference){
        return conferenceEditionRepository.allForConference(idConference);
    }

    public void deleteEdition(DeleteEditionRequest deleteEditionRequest) {
        conferenceEditionRepository.delete(deleteEditionRequest.getEdition());
    }

    public ConferenceEdition addEdition(CreateEditionRequest createEditionRequest) {
        ConferenceEdition ed = ConferenceEdition.builder()
                .setName(createEditionRequest.getName())
                .setAbstractsDeadline(createEditionRequest.getAbstractsDeadline())
                .setBiddingDeadline(createEditionRequest.getBiddingDeadline())
                .setConferenceStartDate(createEditionRequest.getConferenceStartDate())
                .setConferenceEndDate(createEditionRequest.getConferenceEndDate())
                .setFullPapersDeadline(createEditionRequest.getFullPapersDeadline())
                .setEvaluationDeadline(createEditionRequest.getEvaluationDeadline())
                .setConference(createEditionRequest.getConference())
                .build();

        return conferenceEditionRepository.save(ed);
    }
}
