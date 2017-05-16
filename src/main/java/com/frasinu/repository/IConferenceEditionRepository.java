package com.frasinu.repository;

import com.frasinu.exception.InexistentException;
import com.frasinu.model.Conference;
import com.frasinu.model.ConferenceEdition;

import java.util.List;

/**
 * Created by cory_ on 16-May-17.
 */
public interface IConferenceEditionRepository {
    ConferenceEdition create(ConferenceEdition conferenceEdition);

    void delete(int id) throws InexistentException;

    ConferenceEdition update(ConferenceEdition conferenceEdition) throws InexistentException;

    List<ConferenceEdition> getAll();

    ConferenceEdition findById(int id) throws InexistentException;
}
