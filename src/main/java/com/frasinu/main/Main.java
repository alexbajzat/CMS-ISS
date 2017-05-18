package com.frasinu.main;

import com.frasinu.model.ProgramCommitteeMember;
import com.frasinu.repository.PCMemberRepository;
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
        List<ProgramCommitteeMember> pcs = applicationContext.getBean(PCMemberRepository.class).findAll();
    }
}
