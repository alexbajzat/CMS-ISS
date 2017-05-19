package com.frasinu.persistance.repository;

import com.frasinu.persistance.model.Conference;
import com.frasinu.persistance.model.ConferenceEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ConferenceEditionRepository extends JpaRepository<ConferenceEdition,Integer> {

}
