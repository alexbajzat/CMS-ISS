package com.frasinu.iss.service;

import com.frasinu.iss.BaseTestClass;
import com.frasinu.iss.exception.LoginException;
import com.frasinu.iss.exception.RegisterException;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.DeleteConferenceRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.CreateEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.DeleteEditionRequest;
import com.frasinu.iss.service.service_requests.user.*;
import javassist.NotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betty on 6/7/2017.
 */
public class UserServiceTest extends BaseTestClass {
    @Autowired
    private UserService userService;
    private List<Integer> addedUsers=new ArrayList<>();
    private ConferenceEdition edition;
    @Autowired
    private ConferenceEditionService editionService;
    @Autowired
    private ConferenceService conferenceService;
    private Conference conf;
    @Before
    public void init() throws RegisterException{
        User u=userService.registerUser(new RegisterUserRequest("testName","test","1234"));
        User u2=userService.registerUser(new RegisterUserRequest("testNameDoi","test2","1234"));
        addedUsers.add(u.getId());
        addedUsers.add(u2.getId());
        conf=conferenceService.addConference(new CreateConferenceRequest("testConf","ceva.com"));
        edition=editionService.addEdition(new CreateEditionRequest("ed",LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),conf));



    }
    @Test
    public void testLogin() throws LoginException{
        userService.checkLogin(new LoginUserRequest("test","1234"));
    }

    @Test(expected = LoginException.class)
    public void testLoginException() throws LoginException{
        userService.checkLogin(new LoginUserRequest("test","12345"));
    }

    @Test(expected = RegisterException.class)
    public void testRegisterException() throws RegisterException{
        userService.registerUser(new RegisterUserRequest("t","test","1234"));
    }
    @Test
    public void testFind() throws NotFoundException{
        User u=userService.findByUsername(new FindByUsernameRequest("test"));
        User u2=userService.findByUsername(new FindByUsernameRequest("test2"));
        if (u==null || u2==null)
            throw new NotFoundException("user not found");
    }

    @Test(expected = NotFoundException.class)
    public void testFindException() throws NotFoundException{
        User u=userService.findByUsername(new FindByUsernameRequest("test100"));
        if (u==null)
            throw new NotFoundException("user not found");
    }
    @Test
    public void testFindById() throws NotFoundException{
        User u=userService.findById(new FindByIdRequest(addedUsers.get(0)));
        if (u==null)
            throw new NotFoundException("user not found");

    }

    @Test(expected = NotFoundException.class)
    public void testFindByIdException() throws NotFoundException{
        User u=userService.findById(new FindByIdRequest(1232343));
        if (u==null)
            throw new NotFoundException("user not found");

    }

    @Test(expected = NotFoundException.class)
    public void testFindIfAuthor() throws NotFoundException {
        Object author=userService.findIfUserIsAuthor(new FindIfUserIsAuthorRequest(addedUsers.get(0),edition.getId()));
        if (author==null)
            throw new NotFoundException("author not found");
    }

    @Test(expected = NotFoundException.class)
    public void testFindIfPC() throws NotFoundException {
        Object pc=userService.findIfUserIsPC(new FindIfUserIsPCRequest(addedUsers.get(0),edition.getId()));
        if (pc==null)
            throw new NotFoundException("PC not found");
    }



    @Test
    public void testUpdate() throws ValidationException{
        User u=userService.updateUser(new UpdateUserRequest(addedUsers.get(0),"testUpdate","test","1234"));
        assert(u.getName().equals("testUpdate"));
    }

    @Test
    public void testDelete() throws ValidationException {
        userService.deleteUser(new DeleteUserRequest(addedUsers.get(1)));
        addedUsers.remove(1);

    }



    @After
    public void end() {
        for (int id: addedUsers)
            userService.deleteUser(new DeleteUserRequest(id));
        editionService.deleteEdition(new DeleteEditionRequest(edition));
        conferenceService.deleteConference(new DeleteConferenceRequest(conf.getId()));

    }

}
