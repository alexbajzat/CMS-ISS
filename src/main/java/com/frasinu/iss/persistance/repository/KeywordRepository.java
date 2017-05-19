package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Keyword;
import com.frasinu.iss.persistance.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjz on 5/20/2017.
 */
@Transactional
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    @Query(value = "select distinct * from keyword k join proposal_keyword p where k.value in :keywords", nativeQuery= true)
    List<Keyword> findProposalForKeywords(@Param(value = "keywords") List<String> keywords);
}
