package com.frasinu.iss.service;

import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.persistance.repository.AuthorRepository;
import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.iss.service.service_requests.author.FindAllByConferenceEditionRequest;
import com.frasinu.iss.service.service_requests.author.FindUserIdRequest;
import com.frasinu.iss.service.service_requests.author.UpdateAuthorRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(CreateAuthorRequest createAuthorRequest) {
        Author author = Author.builder()
                .setAffiliation(createAuthorRequest.getAffiliation())
                .setEmail(createAuthorRequest.getEmail())
                .setUser(createAuthorRequest.getUser())
                .setConferenceEdition((createAuthorRequest.getConferenceEdition()))
                .build();

        return authorRepository.save(author);
    }

    public Author updateAuthor(UpdateAuthorRequest updateAuthorRequest) throws InexistentException {

        Author author = Author.builder()
                .setId(updateAuthorRequest.getIdOfAuthorToUpdate())
                .setAffiliation(updateAuthorRequest.getAffiliation())
                .setEmail(updateAuthorRequest.getEmail())
                .setUser(updateAuthorRequest.getUser())
                .setConferenceEdition((updateAuthorRequest.getConferenceEdition()))
                .build();


        if (authorRepository.findOne(updateAuthorRequest.getIdOfAuthorToUpdate()) == null) {
            throw new InexistentException("No such author!");
        }
        return authorRepository.save(author);
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }



    public Author findByUserId(FindUserIdRequest findByUserIdRequest) {
        return authorRepository.findByUserId(findByUserIdRequest.getId());
    }

    public Author findById(FindByIdRequest findByIdRequest) {
        return authorRepository.findById(findByIdRequest.getId());
    }

    public List<Author> findAllByConferenceEdition(FindAllByConferenceEditionRequest findAllByConferenceEditionRequest){
        return authorRepository.findAllByConferenceEdition(findAllByConferenceEditionRequest.getConferenceEditionId());
    }
}

