package com.frasinu.service;

import com.frasinu.exception.InexistentException;
import com.frasinu.exception.LoginException;
import com.frasinu.exception.ValidateException;
import com.frasinu.model.User;
import com.frasinu.repository.mysql_db.UserRepository;
import com.frasinu.service.service_requests.LoginUserRequest;
import com.frasinu.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import com.frasinu.service.service_requests.RegisterUserRequest;
import com.frasinu.service.service_requests.DeleteUserRequest;
import com.frasinu.service.service_requests.UpdateUserRequest;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
@Service
public class UserService implements IUserService {
    private UserRepository userRepositoryDB;
    private UserValidator userValidator;

    @Autowired
    public void setUserRepositoryDB(UserRepository userRepositoryDB) {
        this.userRepositoryDB = userRepositoryDB;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator){
        this.userValidator=userValidator;
    }

    @Override
    public User registerUser(RegisterUserRequest registerUserRequest) throws ValidateException {
        String name = registerUserRequest.getName();
        String username = registerUserRequest.getUsername();
        String password = registerUserRequest.getPassword();

        User user = User.builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .build();
        userValidator.validare(user);
        return userRepositoryDB.create(user);
    }

    @Override
    public void deleteUser(DeleteUserRequest deleteUserRequest) throws InexistentException {
        int id = deleteUserRequest.getId();
        userRepositoryDB.delete(id);
    }

    @Override
    public User updateUser(UpdateUserRequest updateUserRequest) throws InexistentException, ValidateException {
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
        userValidator.validare(user);
        return userRepositoryDB.update(user);
    }

    @Override
    public void checkLogin(LoginUserRequest loginUserRequest) throws LoginException {
        String username=loginUserRequest.getUsername();
        String password=loginUserRequest.getPassword();
        try{
            User user=userRepositoryDB.findByUsername(username);
            if(!user.getPassword().equals(password))
                throw new LoginException("Invalid password");

        }catch(InexistentException e){
            throw new LoginException("Invalid username");
        }
        // todo hash the password with md5 , hardcode the salt
    }
}
