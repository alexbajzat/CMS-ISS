package com.frasinu.iss.main;

import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bjz on 5/5/2017.
 */
@ComponentScan(basePackages = "com.frasinu.iss")
public class Main {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        ProposalService proposalService = applicationContext.getBean(ProposalService.class);
        CreateProposalRequest createProposalRequest = CreateProposalRequest.builder()
                .setAbstractPaper("test_abs")
                .setFullPaper("full_test")
                .setTitle("test_title")
                .setTopics(Stream.of("topic1", "topic2").collect(Collectors.toList()))
                .setKeywords(Stream.of("keyword1", "keyword2").collect(Collectors.toList()))
                .setAuthorsId(Stream.of(1).collect(Collectors.toList()))
                .build();
        Proposal proposal = proposalService.createProposalForAuthors(createProposalRequest);
    }
}
