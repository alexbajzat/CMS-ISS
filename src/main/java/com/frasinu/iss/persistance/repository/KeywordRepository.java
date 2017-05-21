package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bjz on 5/21/2017.
 */
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    @Query(value = "insert into proposal_keyword(proposal_id,keyword_id) values(:proposal,:keyword)", nativeQuery = true)
    void addKeywordForProposal(@Param(value = "keyword") Integer keywordId, @Param(value = "proposal") Integer proposalId);
}
