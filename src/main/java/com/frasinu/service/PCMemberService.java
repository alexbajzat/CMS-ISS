package com.frasinu.service;

import com.frasinu.persistance.model.ProgramCommitteeMember;
import com.frasinu.persistance.repository.PCMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public class PCMemberService implements IPCMemberService {
    @Autowired
    private PCMemberRepository pcMemberRepository;

    @Override
    public ProgramCommitteeMember addMember(ProgramCommitteeMember programCommitteeMember) {
        return null;
    }

    @Override
    public List<ProgramCommitteeMember> getAllPcMembers() {
        return null;
    }
}
