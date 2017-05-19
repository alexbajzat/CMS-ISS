package com.frasinu.service;

import com.frasinu.persistance.model.Author;
import com.frasinu.service.service_requests.author.CreateAuthorRequest;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
public interface IAuthorService {
    Author addAuthor(CreateAuthorRequest createAuthorRequest);

    List<Author> getAll();

    Author findByUserId(Integer userId);

}
