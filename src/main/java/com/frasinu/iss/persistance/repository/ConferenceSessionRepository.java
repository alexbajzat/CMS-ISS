package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.ConferenceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ConferenceSessionRepository extends JpaRepository<ConferenceSession,Integer> {
    @Query(value = "select * from conference_session ce where ce.id_conference_edition = :id" , nativeQuery = true)
    List<ConferenceSession> findByConferenceEditionId(@Param("id") Integer id);
}
