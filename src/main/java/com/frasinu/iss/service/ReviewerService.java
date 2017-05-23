package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.repository.ReviewerRepository;
import com.frasinu.iss.service.service_requests.reviewer.FindByUserIdRequest;
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

    public Reviewer addMember(Reviewer reviewer) {
        return null;
    }

    public List<Reviewer> getAllReviewers() {
        return null;
    }

    public Reviewer findByUserId(FindByUserIdRequest findByUserIdRequest){
        return reviewerRepository.findByUserId(findByUserIdRequest.getIdUser(),findByUserIdRequest.getIdEdition());
    }
}
