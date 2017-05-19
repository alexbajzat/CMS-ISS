package com.frasinu.main;

import com.frasinu.exception.LoginException;
import com.frasinu.persistance.model.*;
import com.frasinu.persistance.repository.ConferenceEditionRepository;
import com.frasinu.persistance.repository.ConferenceRepository;
import com.frasinu.service.AuthorService;
import com.frasinu.service.ProposalService;
import com.frasinu.service.UserService;
import com.frasinu.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.service.service_requests.proposal.CreateProposalForAuthorRequest;
import com.frasinu.service.service_requests.proposal.CreateProposalRequest;
import com.frasinu.service.service_requests.user.LoginUserRequest;
import com.frasinu.service.service_requests.user.RegisterUserRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Date;
import java.util.List;

/**
 * Created by bjz on 5/5/2017.
 */
@ComponentScan(basePackages = "com.frasinu")
public class Main {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        AuthorService authorService = applicationContext.getBean(AuthorService.class);
        ProposalService proposalService = applicationContext.getBean(ProposalService.class);
        UserService userService = applicationContext.getBean(UserService.class);
//        User user = userService.registerUser(new RegisterUserRequest("test", "boss_test", "boss_parola"));
//        Author author = authorService.addAuthor(new CreateAuthorRequest("test_add", "email_test", user.getId()));
//        Proposal proposal = proposalService.createProposal(new CreateProposalRequest("test_title", "test_abstract", "test_Full"));
//        Author found = authorService.findByUserId(author.getUserId());

        ConferenceRepository conferenceRepository=applicationContext.getBean(ConferenceRepository.class);
        Conference conference=Conference.builder().setName("Conferinta de pasari").setWebpage("").build();
        conferenceRepository.save(conference);
        ConferenceEditionRepository conferenceEditionRepository=applicationContext.getBean(ConferenceEditionRepository.class);
        conferenceEditionRepository.save(ConferenceEdition.builder().setName("Editia I").setConferenceStartDate(new Date(2017,01,01)).setConferenceEndDate(new Date(2017,01,01))
                .setAbstractsDeadline(new Date(2017,01,01)).setFullPapersDeadline(new Date(2017,01,01)).setBiddingDeadline(new Date(2017,01,01))
                .setEvaluationDeadline(new Date(2017,01,01)).setConference(conference).build());
    }
}
