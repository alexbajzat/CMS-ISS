package com.frasinu.iss.main;

import com.frasinu.iss.persistance.model.Keyword;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.KeywordService;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bjz on 5/5/2017.
 */
@ComponentScan(basePackages = "com.frasinu.iss")
public class Main {
    public static void main(String args[]) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        AuthorService authorService = applicationContext.getBean(AuthorService.class);
        ProposalService proposalService = applicationContext.getBean(ProposalService.class);
        UserService userService = applicationContext.getBean(UserService.class);
        KeywordService keywordService = applicationContext.getBean(KeywordService.class);
        List<Keyword> keywords = keywordService.findProposalForKeywords(Stream.of("ceva").collect(Collectors.toList()));
    }
}
