package com.frasinu.repository;

import com.frasinu.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ConferenceRepository extends JpaRepository<Conference,Integer> {

}