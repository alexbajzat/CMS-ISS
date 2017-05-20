package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ConferenceRepository extends JpaRepository<Conference,Integer> {
    @Query(value = "select * from conference c where c.id = :id" , nativeQuery = true)
    Conference findByConferenceId(@Param("id") Integer id);
}
