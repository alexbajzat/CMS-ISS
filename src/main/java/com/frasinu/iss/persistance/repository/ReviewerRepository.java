package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bjz on 5/21/2017.
 */
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {
}
