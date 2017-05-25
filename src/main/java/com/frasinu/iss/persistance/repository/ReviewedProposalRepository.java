package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.ReviewedProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Andrei on 24-May-17.
 */
@Transactional
public interface ReviewedProposalRepository extends JpaRepository<ReviewedProposal, Integer> {
    @Query(value = "select * from reviewed_proposal rp where rp.reviewer = :id_reviewer and rp.proposal = :id_proposal" , nativeQuery = true)
    ReviewedProposal findByReviewerAndProposal(@Param("id_reviewer") Integer idReviewer, @Param("id_proposal") Integer idProposal);

    @Transactional
    @Modifying
    @Query(value = "insert into reviewed_proposal(review, proposal, reviewer) values (:review,:id_proposal, :id_reviewer)" , nativeQuery = true)
    Integer addReviewPropposal(@Param("id_reviewer") Integer idReviewer, @Param("id_proposal") Integer idProposal, @Param("review") String review);
}
