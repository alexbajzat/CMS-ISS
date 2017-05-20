package com.frasinu.iss.service;

import com.frasinu.iss.exception.LoginException;
import com.frasinu.iss.exception.RegisterException;
import com.frasinu.iss.service.service_requests.user.DeleteUserRequest;
import com.frasinu.iss.service.service_requests.user.LoginUserRequest;
import com.frasinu.iss.service.service_requests.user.RegisterUserRequest;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.service_requests.user.UpdateUserRequest;

import javax.xml.bind.ValidationException;

/**
 * Created by bjz on 5/7/2017.
 */
public interface IUserService {
    User registerUser(RegisterUserRequest registerUserRequest) throws RegisterException;

    void deleteUser(DeleteUserRequest deleteUserRequest);

    User updateUser(UpdateUserRequest updateUserRequest) throws ValidationException;

    void checkLogin(LoginUserRequest loginUserRequest) throws LoginException;
}