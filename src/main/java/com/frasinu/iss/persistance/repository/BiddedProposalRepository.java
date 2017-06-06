package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.BiddedProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bjz on 6/5/2017.
 */
public interface BiddedProposalRepository extends JpaRepository<BiddedProposal,Integer>{
    @Query(value = "select bd.* from bidded_proposal bd inner join reviewer r on bd.reviewer_id = r.id " +
            "where r.id_conference_edition = :edition and bd.reviewer_id = :reviewer" , nativeQuery = true)
    List<BiddedProposal> findAllByReviewerAndEdition(@Param("reviewer") Integer idReviewer, @Param("edition") Integer idEdition);

}
