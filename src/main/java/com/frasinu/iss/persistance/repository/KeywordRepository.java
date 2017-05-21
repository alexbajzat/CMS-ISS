package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bjz on 5/21/2017.
 */
public interface KeywordRepository extends JpaRepository<Keyword,Integer> {
}
