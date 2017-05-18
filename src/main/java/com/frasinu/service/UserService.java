package com.frasinu.service;

import com.frasinu.exception.DuplicatedValueException;
import com.frasinu.exception.InexistentException;
import com.frasinu.exception.LoginException;
import com.frasinu.persistance.model.User;
import com.frasinu.persistance.repository.UserRepository;
import com.frasinu.service.service_requests.user.LoginUserRequest;
import com.frasinu.service.service_requests.user.RegisterUserRequest;
import com.frasinu.service.service_requests.user.DeleteUserRequest;
import com.frasinu.service.service_requests.user.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bjz on 5/7/2017.
 */
@Service
public class UserService implements IUserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(RegisterUserRequest registerUserRequest) {
        String name = registerUserRequest.getName();
        String username = registerUserRequest.getUsername();
        String password = registerUserRequest.getPassword();

        User user = User.builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .build();

        if (userRepository.findByUsername(username) != null) {
            throw new DuplicatedValueException("User already exists!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(DeleteUserRequest deleteUserRequest) throws InexistentException {
        int id = deleteUserRequest.getId();
        userRepository.delete(id);
    }

    @Override
    public User updateUser(UpdateUserRequest updateUserRequest) throws InexistentException {
        int id = updateUserRequest.getIdOfUserToUpdate();
        String name = updateUserRequest.getNewName();
        String username = updateUserRequest.getNewUsername();
        String password = updateUserRequest.getNewPassword();

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

    @Override
    public void checkLogin(LoginUserRequest loginUserRequest) throws LoginException {
        //todo if username and password mismatch , throw a LoginException
        // todo hash the password with md5 , hardcode the salt
        //todo create a LoginException (Exception not Runtime)
    }
}
