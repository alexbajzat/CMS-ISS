package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.repository.ConferenceEditionRepository;
import com.frasinu.iss.service.service_requests.conferenceedition.*;
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

    public ConferenceEdition updateEdition(UpdateEditionRequest updateEditionRequest) {
        ConferenceEdition ed = ConferenceEdition.builder()
                .setId(updateEditionRequest.getId())
                .setName(updateEditionRequest.getName())
                .setAbstractsDeadline(updateEditionRequest.getAbstractsDeadline())
                .setBiddingDeadline(updateEditionRequest.getBiddingDeadline())
                .setConferenceStartDate(updateEditionRequest.getConferenceStartDate())
                .setConferenceEndDate(updateEditionRequest.getConferenceEndDate())
                .setFullPapersDeadline(updateEditionRequest.getFullPapersDeadline())
                .setEvaluationDeadline(updateEditionRequest.getEvaluationDeadline())
                .setConference(updateEditionRequest.getConference())
                .build();

        return conferenceEditionRepository.save(ed);
    }
}
