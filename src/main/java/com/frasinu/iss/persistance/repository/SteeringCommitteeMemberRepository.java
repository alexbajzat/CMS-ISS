package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.model.SteeringCommitteeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjz on 5/21/2017.
 */
@Transactional
public interface SteeringCommitteeMemberRepository extends JpaRepository<SteeringCommitteeMember, Integer> {
    @Query(value = "select * from steering_committee_member a where a.id_user = :id and a.id_conference_edition=:id_conference_edition" , nativeQuery = true)
    SteeringCommitteeMember findByUserAndEditionId(@Param("id") Integer userId, @Param(value = "id_conference_edition") int idConferenceEdition);

}
