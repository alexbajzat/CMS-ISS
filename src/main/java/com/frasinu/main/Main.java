package com.frasinu.main;

import com.frasinu.persistance.model.Author;
import com.frasinu.persistance.model.Proposal;
import com.frasinu.persistance.model.User;
import com.frasinu.service.AuthorService;
import com.frasinu.service.ProposalService;
import com.frasinu.service.UserService;
import com.frasinu.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.service.service_requests.proposal.CreateProposalForAuthorRequest;
import com.frasinu.service.service_requests.proposal.CreateProposalRequest;
import com.frasinu.service.service_requests.user.RegisterUserRequest;
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
        AuthorService authorService = applicationContext.getBean(AuthorService.class);
        ProposalService proposalService = applicationContext.getBean(ProposalService.class);
        UserService userService = applicationContext.getBean(UserService.class);
        User user = userService.registerUser(new RegisterUserRequest("test", "boss_test", "boss_parola"));
        Author author = authorService.addAuthor(new CreateAuthorRequest("test_add", "email_test", user.getId()));
        Proposal proposal = proposalService.createProposal(new CreateProposalRequest("test_title", "test_abstract", "test_Full"));

    }
}
