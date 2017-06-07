package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Listener;
import com.frasinu.iss.persistance.repository.ListenerRepository;
import com.frasinu.iss.service.service_requests.listener.CreateListenerRequest;
import com.frasinu.iss.service.service_requests.listener.FindByUserAndSessionIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cory_ on 07-Jun-17.
 */
@Service
public class ListenerService {
    @Autowired
    private ListenerRepository listenerRepository;

    public List<Listener> findByConferenceSessionIAndUser(FindByUserAndSessionIdRequest findByUserAndSessionIdRequest){
        return listenerRepository.findByUserIdSessionId(findByUserAndSessionIdRequest.getIdUser(),findByUserAndSessionIdRequest.getIdSession());
    }

    public Listener addListener(CreateListenerRequest createListenerRequest) {
        Listener listener=Listener.builder()
                .setUser(createListenerRequest.getUser())
                .setConferenceSession(createListenerRequest.getConferenceSession())
                .build();

        return listenerRepository.save(listener);
    }
}
