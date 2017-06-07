package com.frasinu.iss.service;

import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.persistance.repository.AuthorRepository;
import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.service.service_requests.author.*;
import com.frasinu.iss.service.service_requests.reviewer.FindByUserAndEditionIdRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public Author findByUserIdEditionId(FindByUserAndEditionIdRequest findByUserIdRequest) {
        return authorRepository.findByUserIdEditionId(findByUserIdRequest.getIdUser(),findByUserIdRequest.getIdEdition());
    }

    public Author findById(FindByIdRequest findByIdRequest) {
        return authorRepository.findOne(findByIdRequest.getId());
    }

    public List<Author> findAllByConferenceEdition(FindAllByConferenceEditionRequest findAllByConferenceEditionRequest){
        return authorRepository.findAllByConferenceEdition(findAllByConferenceEditionRequest.getConferenceEditionId());
    }

    public List<Proposal> findProposals(FindProposalsRequest findProposalsRequest){
        return authorRepository.findOne(findProposalsRequest.getId()).getProposals();
    }

    public void deleteAuthor(int id) {
        authorRepository.delete(id);
    }
}

