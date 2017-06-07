package com.frasinu.iss.service;

import com.frasinu.iss.BaseTestClass;
import com.frasinu.iss.exception.RegisterException;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.SteeringCommitteeMember;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.DeleteConferenceRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.CreateEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.DeleteEditionRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.CreateSteeringRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindByUserAndConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindChairByEditionRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.UpdateSteeringRequest;
import com.frasinu.iss.service.service_requests.user.DeleteUserRequest;
import com.frasinu.iss.service.service_requests.user.RegisterUserRequest;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.providers.encoding.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betty on 6/7/2017.
 */
public class SteeringServiceTest extends BaseTestClass {
    @Autowired
    private SteeringCommitteeMemberService steeringService;
    private List<Integer> addedSteerings=new ArrayList<>();
    @Autowired
    private UserService userService;
    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private ConferenceEditionService conferenceEditionService;
    private User user;
    private User user2;
    private Conference conference;
    private ConferenceEdition conferenceEdition;
    @Before
    public void init() throws RegisterException{
        user=userService.registerUser(new RegisterUserRequest("test","test","1234"));
        user2=userService.registerUser(new RegisterUserRequest("testDoi","test2","1234"));

        conference=conferenceService.addConference(new CreateConferenceRequest("test","test"));
        conferenceEdition=conferenceEditionService.addEdition(new CreateEditionRequest("testEd", LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),conference));

        SteeringCommitteeMember s1=steeringService.addSteering(new CreateSteeringRequest("Chair",conferenceEdition,user));
        SteeringCommitteeMember s2=steeringService.addSteering(new CreateSteeringRequest("",conferenceEdition,user2));
        addedSteerings.add(s1.getId());
        addedSteerings.add(s2.getId());
    }

    @Test
    public void testChair(){
        System.out.println("\n\n\n\n "+conferenceEdition+"\n\n\n "+conferenceEdition.getId()+"\n conference"+conference.getId());
        SteeringCommitteeMember chair=steeringService.getChairByEdition(new FindChairByEditionRequest(conferenceEdition));
        assert(chair!=null);
    }
    @Test
    public void test() {
        SteeringCommitteeMember steering=steeringService.updateSteering(new UpdateSteeringRequest(addedSteerings.get(0),"",conferenceEdition,user));
        assert(steering.getRank().equals(""));
        SteeringCommitteeMember chair=steeringService.getChairByEdition(new FindChairByEditionRequest(conferenceEdition));
        assert(chair==null);
        SteeringCommitteeMember steering2=steeringService.updateSteering(new UpdateSteeringRequest(addedSteerings.get(0),"Chair",conferenceEdition,user));
        assert(steering2.getRank().equals("Chair"));

    }

    @Test
    public void testFind() {
        SteeringCommitteeMember steering=steeringService.findByUserAndConferenceEditionId(new FindByUserAndConferenceEditionIdRequest(user.getId(),conferenceEdition.getId()));
        assert (steering.getId().equals(addedSteerings.get(0)));
    }


    @After
    public void end() {
        for(int id:addedSteerings)
            steeringService.deleteSteering(id);
        conferenceEditionService.deleteEdition(new DeleteEditionRequest(conferenceEdition));
        conferenceService.deleteConference(new DeleteConferenceRequest(conference.getId()));
        userService.deleteUser(new DeleteUserRequest(user.getId()));
        userService.deleteUser(new DeleteUserRequest(user2.getId()));
    }
}
