package com.frasinu.service;

import com.frasinu.persistance.model.Conference;
import com.frasinu.persistance.model.Proposal;
import com.frasinu.persistance.repository.ConferenceRepository;
import com.frasinu.persistance.repository.ProposalRepository;
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
}
