package com.frasinu.iss.main;

import com.frasinu.iss.config.AppConfig;
import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalRequest;
import com.frasinu.iss.validator.ProposalValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.xml.bind.ValidationException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bjz on 5/5/2017.
 */
@ComponentScan(basePackages = "com.frasinu.iss")
public class Main {
    public static void main(String args[]) throws InexistentException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        ProposalValidator proposalValidator= applicationContext.getBean(ProposalValidator.class);
        System.out.printf("fafa");
    }
}
