package com.frasinu.persistance.repository;

import com.frasinu.exception.InexistentException;
import com.frasinu.persistance.model.User;

import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
public interface IUserRepository {
    User create(User user);

    void delete(int id) throws InexistentException;

    User update(User user) throws InexistentException;

    List<User> getAll();

    User findById(int id) throws InexistentException;
}
