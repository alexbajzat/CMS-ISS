package com.frasinu.iss.service;

import com.frasinu.iss.persistance.repository.AuthorRepository;
import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.service.service_requests.author.CreateAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(CreateAuthorRequest createAuthorRequest) {
        Author author = Author.builder()
                .setAffiliation(createAuthorRequest.getAffiliation())
                .setEmail(createAuthorRequest.getEmail())
                .setUser(createAuthorRequest.getIdUser())
                .build();

        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findByUserId(Integer userId) {
        return authorRepository.findByUserId(userId);
    }
}

