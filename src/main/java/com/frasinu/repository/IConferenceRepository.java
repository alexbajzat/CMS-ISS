package com.frasinu.repository;

import com.frasinu.exception.InexistentException;
import com.frasinu.model.Conference;

import java.util.List;

/**
 * Created by cory_ on 16-May-17.
 */
public interface IConferenceRepository {
    Conference create(Conference conference);

    void delete(int id) throws InexistentException;

    Conference update(Conference conference) throws InexistentException;

    List<Conference> getAll();

    Conference findById(int id) throws InexistentException;

}
