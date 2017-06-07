package com.frasinu.iss.service;

import com.frasinu.iss.BaseTestClass;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.DeleteConferenceRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betty on 6/7/2017.
 */
public class ConferenceEditionServiceTest extends BaseTestClass {
    @Autowired
    private ConferenceEditionService conferenceEditionService;
    private  List<Integer> addedEditions = new ArrayList<>();
    @Autowired
    private  ConferenceService conferenceService;
    private  Conference conference;

    @Before
    public void init() {
        conference=conferenceService.addConference(new CreateConferenceRequest("testConf","lala"));
        ConferenceEdition e1=conferenceEditionService.addEdition(new CreateEditionRequest("testEd1", LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),conference));
        addedEditions.add(e1.getId());
        ConferenceEdition e2=conferenceEditionService.addEdition(new CreateEditionRequest("testEd2", LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),conference));
        addedEditions.add(e2.getId());
    }

    @Test
    public void testConference(){
        ConferenceEdition edition=conferenceEditionService.findByConferenceEditionId(new FindByConferenceEditionIdRequest(addedEditions.get(0)));
        Conference c=conferenceEditionService.findConferenceByConferenceEditionId(new FindConferenceByConferenceEditionIdRequest(edition.getId()));
        assert (c.getId().equals(conference.getId()));
    }
    @Test
    public void testUpdate() {
        ConferenceEdition ed=conferenceEditionService.updateEdition(new UpdateEditionRequest(addedEditions.get(0),"testEd1Updated",LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),LocalDate.now(),conference));
        assert(ed.getName().equals("testEd1Updated"));
    }
    @Test
    public void testNrEditions() {
        int nrEditions=conferenceEditionService.findEditionsFotConference(conference.getId()).size();
        assert(nrEditions==2);
    }

    @After
    public void end() {
        for (int id : addedEditions) {
            ConferenceEdition edition = conferenceEditionService.findByConferenceEditionId(new FindByConferenceEditionIdRequest(id));
            conferenceEditionService.deleteEdition(new DeleteEditionRequest(edition));
        }
        conferenceService.deleteConference(new DeleteConferenceRequest(conference.getId()));

    }

}