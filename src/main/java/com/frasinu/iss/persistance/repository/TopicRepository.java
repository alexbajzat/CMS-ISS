package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjz on 5/21/2017.
 */
@Transactional
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into proposal_topic(proposal_id,topic_id) values(:proposal,:topic)", nativeQuery = true)
    void addTopicForProposal(@Param(value = "topic") Integer topicId, @Param(value = "proposal") Integer proposalId);

    @Transactional
    @Modifying
    @Query(value = "delete from proposal_topic where proposal_id =:proposal", nativeQuery = true)
    void deleteTopicForProposal(@Param(value = "proposal") Integer proposal);
}
