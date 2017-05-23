package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.repository.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public class ReviewerService {
    @Autowired
    private ReviewerRepository reviewerRepository;

    public Reviewer addMember(Reviewer reviewer) {
        return null;
    }

    public List<Reviewer> getAllReviewers() {
        return null;
    }
}
