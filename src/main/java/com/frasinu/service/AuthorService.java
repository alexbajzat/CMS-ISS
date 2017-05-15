package com.frasinu.service;

import com.frasinu.exception.InexistentException;
import com.frasinu.model.Author;
import com.frasinu.repository.mysql_db.AuthorRepository;
import com.frasinu.service.service_requests.AddAuthorRequest;
import com.frasinu.service.service_requests.DeleteAuthorRequest;
import com.frasinu.service.service_requests.UpdateAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ericqw on 15.05.2017.
 */
@Service
public class AuthorService implements IAuthorService {
    private AuthorRepository authorRepository;
    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){this.authorRepository=authorRepository;}

    @Override
    public Author addAuthor(AddAuthorRequest addAuthorRequest) {
        String affiliation = addAuthorRequest.getAffiliation();
        String email = addAuthorRequest.getEmail();

        Author author = Author.builder()
                .setAffiliation(affiliation)
                .setEmail(email)
                .build();

        return authorRepository.create(author);
    }

    @Override
    public void deleteAuthor(DeleteAuthorRequest deleteAuthorRequest) throws InexistentException {
        int id = deleteAuthorRequest.getId();
        authorRepository.delete(id);
    }

    @Override
    public Author updateAuthor(UpdateAuthorRequest updateAuthorRequest) throws InexistentException {
        int id=updateAuthorRequest.getIdOfAuthorToUpdate();
        String affiliation = updateAuthorRequest.getNewAffiliation();
        String email = updateAuthorRequest.getNewEmail();

        Author author = Author.builder()
                .setId(id)
                .setAffiliation(affiliation)
                .setEmail(email)
                .build();

        return authorRepository.update(author);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.getAll();
    }
}
