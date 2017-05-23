package com.frasinu.iss.service;

import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.repository.ReviewerRepository;
import com.frasinu.iss.service.service_requests.reviewer.CreateReviewerRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindReviewerByIdRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindByUserAndEditionIdRequest;
import com.frasinu.iss.service.service_requests.reviewer.UpdateReviewerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Service
public class ReviewerService {
    @Autowired
    private ReviewerRepository reviewerRepository;

    public Reviewer addReviewer(CreateReviewerRequest createReviewerRequest) {
        Reviewer reviewer = Reviewer.builder()
                .setAffiliation(createReviewerRequest.getAffiliation())
                .setEmail(createReviewerRequest.getEmail())
                .setWebpage(createReviewerRequest.getWebpage())
                .setUser(createReviewerRequest.getUser())
                .setConferenceEdition((createReviewerRequest.getConferenceEdition()))
                .build();

        return reviewerRepository.save(reviewer);
    }

    public Reviewer updateReviewer(UpdateReviewerRequest updateReviewerRequest) throws InexistentException {

        Reviewer reviewer = Reviewer.builder()
                .setId(updateReviewerRequest.getIdOfReviewerToUpdate())
                .setAffiliation(updateReviewerRequest.getAffiliation())
                .setEmail(updateReviewerRequest.getEmail())
                .setWebpage(updateReviewerRequest.getWebpage())
                .setUser(updateReviewerRequest.getUser())
                .setConferenceEdition((updateReviewerRequest.getConferenceEdition()))
                .build();


        if (reviewerRepository.findOne(updateReviewerRequest.getIdOfReviewerToUpdate()) == null) {
            throw new InexistentException("No such Reviewer!");
        }
        return reviewerRepository.save(reviewer);
    }

    public List<Reviewer> getAllReviewers() {
        return null;
    }

    public Reviewer findByUserAndEditionId(FindByUserAndEditionIdRequest findByUserAndEditionIdRequest){
        return reviewerRepository.findByUserAndEditionId(findByUserAndEditionIdRequest.getIdUser(),findByUserAndEditionIdRequest.getIdEdition());
    }

    public Reviewer findById(FindReviewerByIdRequest findByUserIdRequest){
        return reviewerRepository.findById(findByUserIdRequest.getId());
    }
}
