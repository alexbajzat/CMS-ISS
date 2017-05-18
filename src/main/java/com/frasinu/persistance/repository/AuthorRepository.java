package com.frasinu.persistance.repository;

import com.frasinu.persistance.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = "select a from author a where a.id_user = :id" , nativeQuery = true)
    Author findByUserId(@Param("id") Integer userId);
}
