package com.frasinu.iss.service;

import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.exception.LoginException;
import com.frasinu.iss.exception.RegisterException;
import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.persistance.repository.UserRepository;
import com.frasinu.iss.service.service_requests.user.*;
import com.frasinu.iss.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
@Transactional
@Service
public class UserService {
    private UserRepository userRepository;
    private UserValidator userValidator = new UserValidator();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterUserRequest registerUserRequest) throws RegisterException {
        String name = registerUserRequest.getName();
        String username = registerUserRequest.getUsername();
        String password = registerUserRequest.getPassword();

        PasswordEncoder encoder = new Md5PasswordEncoder();

        User user = User.builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .build();

        try {
            userValidator.validate(user);
        } catch (ValidationException e) {
            throw new RegisterException(e.getMessage());
        }

        user = User.builder()
                .setName(name)
                .setUsername(username)
                .setPassword(encoder.encodePassword(password, null))
                .build();

        if (userRepository.findByUsername(username) != null) {
            throw new RegisterException("User already exists!");
        }
        return userRepository.save(user);
    }

    public void deleteUser(DeleteUserRequest deleteUserRequest) throws InexistentException {
        int id = deleteUserRequest.getId();
        userRepository.delete(id);
    }

    public User updateUser(UpdateUserRequest updateUserRequest) throws InexistentException, ValidationException {
        int id = updateUserRequest.getIdOfUserToUpdate();
        String name = updateUserRequest.getNewName();
        String username = updateUserRequest.getNewUsername();
        String password = updateUserRequest.getNewPassword();

        PasswordEncoder encoder = new Md5PasswordEncoder();

        User user = User.builder()
                .setId(id)
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .build();
        userValidator.validate(user);

        user = User.builder()
                .setName(name)
                .setUsername(username)
                .setPassword(encoder.encodePassword(password, null))
                .build();

        if (userRepository.findOne(id) == null) {
            throw new InexistentException("No such user!");
        }
        return userRepository.save(user);
    }

    public User updateUserPasswordEncoded(UpdateUserRequest updateUserRequest) throws InexistentException, ValidationException {
        int id = updateUserRequest.getIdOfUserToUpdate();
        String name = updateUserRequest.getNewName();
        String username = updateUserRequest.getNewUsername();
        String password = updateUserRequest.getNewPassword();

        PasswordEncoder encoder = new Md5PasswordEncoder();

        User user = User.builder()
                .setId(id)
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .build();

        if (userRepository.findOne(id) == null) {
            throw new InexistentException("No such user!");
        }
        return userRepository.save(user);
    }

    public void checkLogin(LoginUserRequest loginUserRequest) throws LoginException {
        String username = loginUserRequest.getUsername();
        String password = loginUserRequest.getPassword();
        PasswordEncoder encoder = new Md5PasswordEncoder();
        password = encoder.encodePassword(password, null);
        User user = null;
        user = userRepository.findByUsername(username);
        if (user == null)
            throw new LoginException("Invalid username");
        if (!user.getPassword().equals(password))
            throw new LoginException("Invalid password");

    }

    public User findByUsername(FindByUsernameRequest findByUsernameRequest) {
        return userRepository.findByUsername(findByUsernameRequest.getUsername());
    }


    public Author findIfUserIsAuthor(FindIfUserIsAuthorRequest findIfUserIsAuthorRequest){
        User user=userRepository.findOne(findIfUserIsAuthorRequest.getIdUser());
        for(Author a:user.getAuthors()){
            if(a.getConferenceEdition().getId()==findIfUserIsAuthorRequest.getIdEdition())
                return a;
        }
        return null;
    }

    public User findById(FindByIdRequest findByIdRequest){
        return userRepository.findOne(findByIdRequest.getId());
    }

    public List<User> getAll(){

        return userRepository.findAll();
    }

    public Reviewer findIfUserIsPC(int idUser, int idEdition) {
        User user=userRepository.findById(idUser);
        for(Reviewer r:user.getReviewers()){
            System.out.println("/n");
            System.out.println(idEdition);
            System.out.println(r.getConferenceEdition().getId());
            if(r.getConferenceEdition().getId()==idEdition)
                return r;
        }
        return null;
    }
}

