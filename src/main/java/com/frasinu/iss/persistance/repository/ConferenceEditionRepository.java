package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ConferenceEditionRepository extends JpaRepository<ConferenceEdition,Integer> {

}