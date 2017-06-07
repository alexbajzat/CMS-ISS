package com.frasinu.iss;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.SteeringCommitteeMemberService;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.CreateSteeringRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.providers.encoding.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Betty on 6/7/2017.
 */
public class SteeringServiceTest extends BaseTestClass {
    private static SteeringCommitteeMemberService steeringService;
    private static List<Integer> addedSteerings;
    @BeforeClass
    public static void init() {
        steeringService = new SteeringCommitteeMemberService();
        PasswordEncoder encoder = new Md5PasswordEncoder();
        User user = User.builder().setName("testName").setUsername("test").setPassword(encoder.encodePassword("1111", null)).build();
        Conference conf = Conference.builder().setName("conf1").setWebpage("ceva.com").build();
        ConferenceEdition ed = ConferenceEdition.builder()
                .setName("Ed1")
                .setAbstractsDeadline(LocalDate.now())
                .setBiddingDeadline(LocalDate.now())
                .setConferenceStartDate(LocalDate.now())
                .setConferenceEndDate(LocalDate.now())
                .setFullPapersDeadline(LocalDate.now())
                .setEvaluationDeadline(LocalDate.now())
                .setConference(conf)
                .build();

        steeringService.addSteering(new CreateSteeringRequest("Chair",ed,user));
    }

    @Test
    public void test(){
        //steeringService.findByUserAndConferenceEditionId(new FindByUserAndConferenceEditionIdRequest());

    }

    @AfterClass
    public static void end() {
        //steeringService.deleteSteering();
    }
}
