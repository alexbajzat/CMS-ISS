package com.frasinu.repository;

import com.frasinu.exception.InexistentException;
import com.frasinu.model.Author;

import java.util.List;

/**
 * Created by Ericqw on 15.05.2017.
 */
public interface IAuthorRepository{

        Author create(Author author);

        void delete(int id) throws InexistentException;

        Author update(Author author) throws InexistentException;

        List<Author> getAll();

        Author findById(int id) throws InexistentException;
}

