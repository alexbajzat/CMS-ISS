package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cory_ on 06-Jun-17.
 */
@Transactional
public interface PresentationRepository extends JpaRepository<Presentation, Integer> {
    @Query(value = "select * from presentation p where p.id_conference_session = :id" , nativeQuery = true)
    List<Presentation> findByConferenceSessionId(@Param("id") Integer id);
}
