package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.ProgramCommitteeMember;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public interface IPCMemberService {
    ProgramCommitteeMember addMember(ProgramCommitteeMember programCommitteeMember);

    List<ProgramCommitteeMember> getAllPcMembers();
}
