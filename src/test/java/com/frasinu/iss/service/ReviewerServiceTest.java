package com.frasinu.iss.service;

import com.frasinu.iss.BaseTestClass;
import com.frasinu.iss.exception.RegisterException;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.DeleteConferenceRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.CreateEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.DeleteEditionRequest;
import com.frasinu.iss.service.service_requests.reviewer.*;
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
 * Created by Betty on 6/8/2017.
 */
public class ReviewerServiceTest extends BaseTestClass {
    @Autowired
    private ReviewerService reviewerService;
    private List<Integer> addedreviewers=new ArrayList<>();
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
    public void init() throws RegisterException {
        user=userService.registerUser(new RegisterUserRequest("test","test","1234"));
        user2=userService.registerUser(new RegisterUserRequest("testDoi","test2","1234"));

        conference=conferenceService.addConference(new CreateConferenceRequest("test","test"));
        conferenceEdition=conferenceEditionService.addEdition(new CreateEditionRequest("testEd", LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),conference));

        Reviewer s1=reviewerService.addReviewer(new CreateReviewerRequest("","","",user,conferenceEdition));
        Reviewer s2=reviewerService.addReviewer(new CreateReviewerRequest("","","",user2,conferenceEdition));
        addedreviewers.add(s1.getId());
        addedreviewers.add(s2.getId());
    }


    @Test
    public void testUpdate() {
        Reviewer rew = reviewerService.updateReviewer(new UpdateReviewerRequest(addedreviewers.get(0), "", "email", "", user, conferenceEdition));
        assert (rew.getEmail().equals("email"));
    }
    @Test
    public void testAssign() {
        boolean res = reviewerService.assignPaperToReviewer(new AssignPaperToReviewerRequest(addedreviewers.get(0), 1, ""));
        assert res == true;
    }
    @Test
    public void testAll() {
        int size=reviewerService.getAllReviewersForEdition(new FindReviewersByEditionRequest(conferenceEdition.getId())).size();
        assert size==2;
    }

    @Test
    public void testFind() {
        Reviewer rew=reviewerService.findByUserAndEditionId(new FindByUserAndEditionIdRequest(user.getId(),conferenceEdition.getId()));
        assert (rew.getId().equals(addedreviewers.get(0)));
    }


    @After
    public void end() {
        for(int id:addedreviewers)
            reviewerService.deleteReviewer(id);
        conferenceEditionService.deleteEdition(new DeleteEditionRequest(conferenceEdition));
        conferenceService.deleteConference(new DeleteConferenceRequest(conference.getId()));
        userService.deleteUser(new DeleteUserRequest(user.getId()));
        userService.deleteUser(new DeleteUserRequest(user2.getId()));
    }
}
