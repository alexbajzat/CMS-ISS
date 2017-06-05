package com.frasinu.iss.persistance.repository;

import com.frasinu.iss.persistance.model.BiddedProposal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bjz on 6/5/2017.
 */
public interface BiddedProposalRepository extends JpaRepository<BiddedProposal,Integer>{
}
