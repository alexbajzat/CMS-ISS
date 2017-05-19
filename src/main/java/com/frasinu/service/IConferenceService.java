package com.frasinu.service;

import com.frasinu.persistance.model.Conference;

import java.util.List;

/**
 * Created by cory_ on 19-May-17.
 */
public interface IConferenceService {
    public List<Conference> getAll();
}
