package com.frasinu.repository;

import com.frasinu.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface ProposalRepository extends JpaRepository<Proposal,Integer> {
}
