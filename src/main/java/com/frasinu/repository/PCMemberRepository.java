package com.frasinu.repository;

import com.frasinu.model.ProgramCommitteeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjz on 5/18/2017.
 */
@Transactional
public interface PCMemberRepository extends JpaRepository<ProgramCommitteeMember,Integer> {
}
