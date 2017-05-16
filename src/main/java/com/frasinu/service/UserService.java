package com.frasinu.service;

import com.frasinu.exception.InexistentException;
import com.frasinu.exception.LoginException;
import com.frasinu.model.User;
import com.frasinu.repository.mysql_db.UserRepository;
import com.frasinu.service.service_requests.LoginUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.frasinu.service.service_requests.RegisterUserRequest;
import com.frasinu.service.service_requests.DeleteUserRequest;
import com.frasinu.service.service_requests.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
@Service
public class UserService implements IUserService {
    private UserRepository userRepositoryDB;

    @Autowired
    public void setUserRepositoryDB(UserRepository userRepositoryDB) {
        this.userRepositoryDB = userRepositoryDB;
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

        return userRepositoryDB.create(user);
    }

    @Override
    public void deleteUser(DeleteUserRequest deleteUserRequest) throws InexistentException {
        int id = deleteUserRequest.getId();
        userRepositoryDB.delete(id);
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

        return userRepositoryDB.update(user);
    }

    @Override
    public void checkLogin(LoginUserRequest loginUserRequest) throws LoginException {
        String username = loginUserRequest.getUsername();
        String password = loginUserRequest.getPassword();

        try {
            User user = userRepositoryDB.findByUsername(username);
            if (!user.getPassword().equals(password))
                throw new LoginException("The password is incorrect");
        }catch(InexistentException e){
            throw new LoginException("The username is incorrect");
        }
        // todo hash the password with md5 , hardcode the salt
    }
}
