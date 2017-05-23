package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjz on 5/21/2017.
 */
@Transactional
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {
}
