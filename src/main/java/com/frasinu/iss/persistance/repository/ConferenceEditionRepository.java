package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ConferenceEditionRepository extends JpaRepository<ConferenceEdition,Integer> {
    @Query(value = "select * from conference_edition ce where ce.id = :id" , nativeQuery = true)
    ConferenceEdition findByConferenceEditionId(@Param("id") Integer id);

    @Query(value = "select * from conference_edition ce where ce.id_conference = :id" , nativeQuery = true)
    List<ConferenceEdition> allForConference(@Param("id") int id);
}
