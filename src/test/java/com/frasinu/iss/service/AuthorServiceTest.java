package com.frasinu.iss.service;

import com.frasinu.iss.BaseTestClass;
import com.frasinu.iss.exception.RegisterException;
import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.service_requests.author.*;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.DeleteConferenceRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.CreateEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.DeleteEditionRequest;
import com.frasinu.iss.service.service_requests.user.DeleteUserRequest;
import com.frasinu.iss.service.service_requests.user.RegisterUserRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by bjz on 6/7/2017.
 */
public class AuthorServiceTest extends BaseTestClass{
    @Autowired
    private AuthorService authorService;
    private List<Integer> addedauthors=new ArrayList<>();
    @Autowired
    private UserService userService;
    private User u,u2;

    private ConferenceEdition edition;
    @Autowired
    private ConferenceEditionService editionService;
    @Autowired
    private ConferenceService conferenceService;
    private Conference conf;

    @Before
    public void init() throws  RegisterException{
        u = userService.registerUser(new RegisterUserRequest("testName", "test", "1234"));
        u2 = userService.registerUser(new RegisterUserRequest("testNameDoi", "test2", "1234"));

        conf=conferenceService.addConference(new CreateConferenceRequest("testConf","ceva.com"));
        edition=editionService.addEdition(new CreateEditionRequest("ed", LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),conf));

        Author author=authorService.addAuthor(new CreateAuthorRequest("","",u,edition));
        Author author2=authorService.addAuthor(new CreateAuthorRequest("","",u2,edition));
        addedauthors.add(author.getId());
        addedauthors.add(author2.getId());

    }

    @Test
    public void testFindAll() {
        int size = authorService.findAllByConferenceEdition(new FindAllByConferenceEditionRequest(edition.getId())).size();
        assert (size == 2);
    }
    @Test
    public void testFindById() {
        Author author = authorService.findByUserId(new FindUserIdRequest(u.getId()));
        assert (author.getId().equals(addedauthors.get(0)));

        Author author2=authorService.findByUserId(new FindUserIdRequest(99999));
        assert(author2==null);
    }
    @Test
    public void testDelete() {

        authorService.deleteAuthor(new DeleteAuthorRequest(addedauthors.get(0)));
        int size2 = authorService.findAllByConferenceEdition(new FindAllByConferenceEditionRequest(edition.getId())).size();
        addedauthors.remove(0);
        assert (size2 == 1);
    }
    @Test
    public void testProposal() {

        int proposalSize = authorService.findProposals(new FindProposalsRequest(addedauthors.get(0))).size();
        assert (proposalSize == 0);
    }
    @Test
    public void testUpdate(){

        authorService.updateAuthor(new UpdateAuthorRequest(addedauthors.get(0),"","melania",u,edition));
        Author a=authorService.findByUserId(new FindUserIdRequest(u.getId()));
        assert(a.getEmail().equals("melania"));


    }

    @After
    public void end() {
        for (int id: addedauthors)
            authorService.deleteAuthor(new DeleteAuthorRequest(id));
        editionService.deleteEdition(new DeleteEditionRequest(edition));
        conferenceService.deleteConference(new DeleteConferenceRequest(conf.getId()));
        userService.deleteUser(new DeleteUserRequest(u.getId()));
        userService.deleteUser(new DeleteUserRequest(u2.getId()));

    }
}