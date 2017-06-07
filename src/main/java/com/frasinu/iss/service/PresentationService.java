package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Presentation;
import com.frasinu.iss.persistance.repository.PresentationRepository;
import com.frasinu.iss.service.service_requests.presentation.CreatePresentationRequest;
import com.frasinu.iss.service.service_requests.presentation.FindByConferenceSessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cory_ on 06-Jun-17.
 */
@Service
public class PresentationService {
    @Autowired
    private PresentationRepository presentationRepository;


    public List<Presentation> findByConferenceSessionId(FindByConferenceSessionRequest findByConferenceSessionRequest){
        return presentationRepository.findByConferenceSessionId(findByConferenceSessionRequest.getId());
    }

    public Presentation addPresentation(CreatePresentationRequest createPresentationRequest) {
        Presentation presentation = Presentation.builder()
                .setAuthor(createPresentationRequest.getAuthor())
                .setTime(createPresentationRequest.getTime())
                .setConferenceSession(createPresentationRequest.getConferenceSession())
                .setProposal(createPresentationRequest.getProposal())
                .build();

        return presentationRepository.save(presentation);
    }
}
