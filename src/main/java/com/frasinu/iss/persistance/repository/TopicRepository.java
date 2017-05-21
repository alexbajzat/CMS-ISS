package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by bjz on 5/21/2017.
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    @Query(value = "insert into proposal_topic(proposal_id,proposal_id) values(:proposal,:topic)", nativeQuery = true)
    void addTopicForProposal(@Param(value = "topic") Integer topicId, @Param(value = "proposal") Integer proposalId);
}
