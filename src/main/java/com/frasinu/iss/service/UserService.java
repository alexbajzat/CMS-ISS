package com.frasinu.service;

import com.frasinu.exception.DuplicatedValueException;
import com.frasinu.exception.InexistentException;
import com.frasinu.exception.LoginException;
import com.frasinu.exception.RegisterException;
import com.frasinu.persistance.model.User;
import com.frasinu.persistance.repository.UserRepository;
import com.frasinu.service.service_requests.user.LoginUserRequest;
import com.frasinu.service.service_requests.user.RegisterUserRequest;
import com.frasinu.service.service_requests.user.DeleteUserRequest;
import com.frasinu.service.service_requests.user.UpdateUserRequest;
import com.frasinu.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

/**
 * Created by bjz on 5/7/2017.
 */
@Service
public class UserService implements IUserService {
    private UserRepository userRepository;
    private UserValidator userValidator=new UserValidator();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(RegisterUserRequest registerUserRequest) throws RegisterException{
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
            userValidator.validare(user);
        } catch (ValidationException e) {
            throw new RegisterException(e.getMessage());
        }

        user=User.builder()
                .setName(name)
                .setUsername(username)
                .setPassword(encoder.encodePassword(password,null))
                .build();

        if (userRepository.findByUsername(username) != null) {
            throw new RegisterException("User already exists!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(DeleteUserRequest deleteUserRequest) throws InexistentException {
        int id = deleteUserRequest.getId();
        userRepository.delete(id);
    }

    @Override
    public User updateUser(UpdateUserRequest updateUserRequest) throws InexistentException,ValidationException {
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
        userValidator.validare(user);
        
        user=User.builder()
                .setName(name)
                .setUsername(username)
                .setPassword(encoder.encodePassword(password,null))
                .build();

        if (userRepository.findOne(id) == null) {
            throw new InexistentException("No such user!");
        }
        return userRepository.save(user);
    }

    @Override
    public void checkLogin(LoginUserRequest loginUserRequest) throws LoginException {
        String username=loginUserRequest.getUsername();
        String password=loginUserRequest.getPassword();
        PasswordEncoder encoder = new Md5PasswordEncoder();
        password=encoder.encodePassword(password,null);
        User user=null;
        user=userRepository.findByUsername(username);
        if(user==null)
            throw new LoginException("Invalid username");
        if(!user.getPassword().equals(password))
            throw new LoginException("Invalid password");

        }


        // todo hash the password with md5 , hardcode the salt

    }

