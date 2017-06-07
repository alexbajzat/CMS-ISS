package com.frasinu.iss;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.ConferenceService;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.CreateEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.DeleteEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindConferenceByConferenceEditionIdRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betty on 6/7/2017.
 */
public class ConferenceEditionServiceTest extends BaseTestClass {
    private static ConferenceEditionService conferenceEditionService;
    private static List<Integer> addedEditions = new ArrayList<>();
    private static ConferenceService conferenceService;
    private static Conference conference;

    @BeforeClass
    public static void init() {
        conferenceService=new ConferenceService();
        conference=conferenceService.addConference(new CreateConferenceRequest("testConf","lala"));
        conferenceEditionService=new ConferenceEditionService();
        conferenceEditionService.addEdition(new CreateEditionRequest("ed", LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),conference));

    }

    @Test
    public void testConference(){
        ConferenceEdition edition=conferenceEditionService.findByConferenceEditionId(new FindByConferenceEditionIdRequest(addedEditions.get(0)));
        Conference c=conferenceEditionService.findConferenceByConferenceEditionId(new FindConferenceByConferenceEditionIdRequest(edition.getId()));
        assert (c.getId()==conference.getId());
    }

    @AfterClass
    public static void end() {
        for (int id : addedEditions) {
            ConferenceEdition edition = conferenceEditionService.findByConferenceEditionId(new FindByConferenceEditionIdRequest(id));
            conferenceEditionService.deleteEdition(new DeleteEditionRequest(edition));
        }

    }

}