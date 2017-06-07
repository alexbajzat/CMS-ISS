package com.frasinu.iss.service;

import com.frasinu.iss.BaseTestClass;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.service.service_requests.conference.ConferenceByIdRequest;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.DeleteConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.UpdateConferenceRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betty on 6/7/2017.
 */
public class ConferenceServiceTest extends BaseTestClass{
    @Autowired
    private ConferenceService conferenceService;
    private  List<Integer> addedConfs=new ArrayList<>();
    @Before
    public void init(){

        Conference c1=conferenceService.addConference(new CreateConferenceRequest("conf1","ceva"));
        Conference c2=conferenceService.addConference(new CreateConferenceRequest("conf2","ceva"));
        addedConfs.add(c1.getId());
        addedConfs.add(c2.getId());
    }
    @Test
    public void testAdd() {
        int oldSize=conferenceService.getAll().size();
        Conference c2=conferenceService.addConference(new CreateConferenceRequest("conf3","ceva"));
        addedConfs.add(c2.getId());
        int newSize=conferenceService.getAll().size();
        assert((oldSize+1)==newSize);
    }


    @Test
    public void testUpdate(){

        conferenceService.updateConference(new UpdateConferenceRequest(addedConfs.get(0),"conf1Updated","ceva"));
        Conference c=conferenceService.getConferenceById(new ConferenceByIdRequest(addedConfs.get(0)));
        assert(c.getName().equals("conf1Updated"));

    }

    @Test
    public void testDelete() {
        int oldSize=conferenceService.getAll().size();
        conferenceService.deleteConference(new DeleteConferenceRequest(addedConfs.get(1)));
        addedConfs.remove(1);
        int newSize=conferenceService.getAll().size();
        assert((oldSize-1)==newSize);

    }

    @After
    public  void end() {

        for (int id : addedConfs)
            conferenceService.deleteConference(new DeleteConferenceRequest(id));

    }
}
