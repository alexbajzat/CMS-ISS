package com.frasinu.iss;

import com.frasinu.iss.exception.LoginException;
import com.frasinu.iss.exception.RegisterException;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.UserService;
import com.frasinu.iss.service.service_requests.conferenceedition.CreateEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.DeleteEditionRequest;
import com.frasinu.iss.service.service_requests.user.*;
import javassist.NotFoundException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betty on 6/7/2017.
 */
public class UserServiceTest extends BaseTestClass {
    private static UserService userService;
    private static List<Integer> addedUsers=new ArrayList<>();
    private static ConferenceEdition edition;
    private static ConferenceEditionService editionService;
    @BeforeClass
    public static void init() throws RegisterException{
        userService=new UserService();
        User u=userService.registerUser(new RegisterUserRequest("testName","test","1234"));
        User u2=userService.registerUser(new RegisterUserRequest("testName2","test2","1234"));
        addedUsers.add(u.getId(),u2.getId());
        Conference conf = Conference.builder().setName("conf1").setWebpage("ceva.com").build();
        editionService=new ConferenceEditionService();
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
        else {
            addedUsers.add(u.getId());
            addedUsers.add(u2.getId());
        }
    }

    @Test(expected = NotFoundException.class)
    public void testFindException() throws NotFoundException{
        User u=userService.findByUsername(new FindByUsernameRequest("test100"));
        if (u==null)
            throw new NotFoundException("user not found");
        else
            addedUsers.add(u.getId());
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
        Object author=userService.findIfUserIsPC(new FindIfUserIsPCRequest(addedUsers.get(0),edition.getId()));
        if (author==null)
            throw new NotFoundException("PC not found");
    }



    @Test
    public void testUpdate() throws ValidationException{
        userService.updateUser(new UpdateUserRequest(addedUsers.get(0),"testUpdate","testUpdate","1234"));
    }

    @Test
    public void testDelete() throws ValidationException {
        userService.deleteUser(new DeleteUserRequest(addedUsers.get(0)));
        addedUsers.remove(0);

    }



    @AfterClass
    public static void end() {
        for (int id: addedUsers)
            userService.deleteUser(new DeleteUserRequest(id));
        editionService.deleteEdition(new DeleteEditionRequest(edition));

    }

}
