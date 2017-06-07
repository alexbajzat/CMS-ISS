package com.frasinu.iss;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.service.ConferenceService;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.DeleteConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.UpdateConferenceRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betty on 6/7/2017.
 */
public class ConferenceServiceTest {
    private static ConferenceService conferenceService;
    private static List<Integer> addedConfs=new ArrayList<>();
    @BeforeClass
    public static void init(){
        conferenceService=new ConferenceService();
        Conference c1=conferenceService.addConference(new CreateConferenceRequest("conf1","ceva"));
        Conference c2=conferenceService.addConference(new CreateConferenceRequest("conf2","ceva"));
        addedConfs.add(c1.getId(),c2.getId());
    }
    @Test
    public void testAdd() {
        int oldSize=conferenceService.getAll().size()+1;
        Conference c2=conferenceService.addConference(new CreateConferenceRequest("conf3","ceva"));
        addedConfs.add(c2.getId());
        int newSize=conferenceService.getAll().size();
        assert((oldSize+1)==newSize);

    }
    @Test
    public void testUpdate(){
        conferenceService.updateConference(new UpdateConferenceRequest(addedConfs.get(0),"conf1Updated","ceva"));



    }

    @AfterClass
    public static void end() {
        for (int id : addedConfs)
            conferenceService.deleteConference(new DeleteConferenceRequest(id));
    }
}
