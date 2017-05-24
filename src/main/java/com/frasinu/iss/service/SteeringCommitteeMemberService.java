package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.SteeringCommitteeMember;
import com.frasinu.iss.persistance.repository.SteeringCommitteeMemberRepository;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindByUserAndConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindSteeringCommitteeMemberByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cory_ on 23-May-17.
 */

@Service
public class SteeringCommitteeMemberService {
    @Autowired
    private SteeringCommitteeMemberRepository steeringCommitteeMemberRepository;

    public SteeringCommitteeMember findByUserAndConferenceEditionId(FindByUserAndConferenceEditionIdRequest findByUserAndConferenceIdRequest){
        return steeringCommitteeMemberRepository.findByUserAndEditionId(findByUserAndConferenceIdRequest.getIdUser(),findByUserAndConferenceIdRequest.getIdEdition());
    }

    public SteeringCommitteeMember findById(FindSteeringCommitteeMemberByIdRequest findSteeringCommitteeMemberByIdRequest){
        return steeringCommitteeMemberRepository.findOne(findSteeringCommitteeMemberByIdRequest.getId());
    }
}
