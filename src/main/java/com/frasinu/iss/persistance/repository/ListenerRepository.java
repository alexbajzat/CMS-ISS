package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Listener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ListenerRepository extends JpaRepository<Listener, Integer> {


    @Query(value = "Select * from listener l  where l.id_conference_session = :idSession and l.id_user=:idUser",nativeQuery = true)
    List<Listener> findByUserIdSessionId(@Param("idUser") int idUser, @Param("idSession") int idSession);
}
