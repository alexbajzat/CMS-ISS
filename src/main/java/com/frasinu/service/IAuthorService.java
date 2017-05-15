package com.frasinu.service;

import com.frasinu.model.Author;
import com.frasinu.service.service_requests.AddAuthorRequest;
import com.frasinu.service.service_requests.DeleteAuthorRequest;
import com.frasinu.service.service_requests.UpdateAuthorRequest;

import java.util.List;

/**
 * Created by Ericqw on 15.05.2017.
 */
public interface IAuthorService {
    Author addAuthor(AddAuthorRequest addAuthorRequest);
    void deleteAuthor(DeleteAuthorRequest deleteAuthorRequest);
    Author updateAuthor(UpdateAuthorRequest updateAuthorRequest);
    List<Author>getAuthors();
}
