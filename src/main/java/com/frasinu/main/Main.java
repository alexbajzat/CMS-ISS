package com.frasinu.main;

import com.frasinu.model.ProgramCommitteeMember;
import com.frasinu.model.Proposal;
import com.frasinu.repository.PCMemberRepository;
import com.frasinu.repository.ProposalRepository;
import com.frasinu.service.ProposalService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * Created by bjz on 5/5/2017.
 */
@ComponentScan(basePackages = "com.frasinu")
public class Main {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        List<Proposal> pcs = applicationContext.getBean(ProposalService.class).getAll();
    }
}
