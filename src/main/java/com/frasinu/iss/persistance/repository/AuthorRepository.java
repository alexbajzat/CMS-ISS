package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = "select * from author a where a.id_user = :id" , nativeQuery = true)
    Author findByUserId(@Param("id") Integer userId);

    @Query(value = "Select * from author a  where a.id_conference_edition = :id_conference_edition",nativeQuery = true)
    List<Author> findAllByConferenceEdition(@Param(value = "id_conference_edition") Integer id_conference_edition);

    @Query(value = "Select * from author a  where a.id_conference_edition = :idEdition and a.id_user=:idUser",nativeQuery = true)
    Author findByUserIdEditionId(@Param("idUser") int idUser,@Param("idEdition") int idEdition);
}
