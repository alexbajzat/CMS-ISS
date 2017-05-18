package com.frasinu.service;

import com.frasinu.model.ProgramCommitteeMember;
import com.frasinu.repository.PCMemberRepository;
import com.frasinu.service.IPCMemberService;
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
