package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.SteeringCommitteeMember;
import com.frasinu.iss.persistance.repository.SteeringCommitteeMemberRepository;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.CreateSteeringRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindByUserAndConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindSteeringCommitteeMemberByIdRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.UpdateSteeringRequest;
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

    public SteeringCommitteeMember addSteering(CreateSteeringRequest createSteeringRequest) {
        SteeringCommitteeMember steering = SteeringCommitteeMember.builder()
                .setAffiliation(createSteeringRequest.getRank())
                .setConferenceEdition(createSteeringRequest.getConferenceEdition())
                .setUser(createSteeringRequest.getUser())
                .build();

        return steeringCommitteeMemberRepository.save(steering);
    }

    public void deleteSteering(Integer id) {
        steeringCommitteeMemberRepository.delete(id);
    }

    public SteeringCommitteeMember updateSteering(UpdateSteeringRequest updateSteeringRequest) {
        SteeringCommitteeMember steering = SteeringCommitteeMember.builder()
                .setId(updateSteeringRequest.getId())
                .setAffiliation(updateSteeringRequest.getRank())
                .setConferenceEdition(updateSteeringRequest.getConferenceEdition())
                .setUser(updateSteeringRequest.getUser())
                .build();

        return steeringCommitteeMemberRepository.save(steering);
    }
}
