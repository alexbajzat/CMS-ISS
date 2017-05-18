package com.frasinu.service;

import com.frasinu.model.ProgramCommitteeMember;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public interface IPCMemberService {
    ProgramCommitteeMember addMember(ProgramCommitteeMember programCommitteeMember);

    List<ProgramCommitteeMember> getAllPcMembers();
}
