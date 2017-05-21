package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ProposalRepository extends JpaRepository<Proposal, Integer> {
    @Transactional
    @Query(value = "Select * from proposal p join user_app ua where ua.id = :author",nativeQuery = true)
    List<Proposal> findAllForAuthor(@Param(value = "author") Integer authorId);

    @Query(value = "Insert into author_proposal(proposal_id, author_id) values(:proposal, :author)",nativeQuery = true)
    void addProposalForAuthor(@Param(value = "proposal") int idProposal ,@Param(value = "author") int idAuthor);
}
