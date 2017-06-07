package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Modifying
    @Query(value = "Insert into author_proposal(proposal_id, author_id) values(:proposal, :author)", nativeQuery = true)
    Integer addProposalForAuthor(@Param(value = "proposal") int idProposal, @Param(value = "author") int idAuthor);

    @Modifying
    @Transactional
    @Query(value = "delete from author_proposal where proposal_id =:proposal", nativeQuery = true)
    void deleteProposalForAuthor(@Param(value = "proposal") Integer proposal);

    @Query(value = "SELECT * FROM Proposal WHERE conferenceEdition_id=:id_edition", nativeQuery = true)
    List<Proposal> findByConferenceEdition(@Param(value = "id_edition") int idEdition);
}
