package com.frasinu.service;

import com.frasinu.exception.LoginException;
import com.frasinu.persistance.model.User;
import com.frasinu.service.service_requests.user.LoginUserRequest;
import com.frasinu.service.service_requests.user.RegisterUserRequest;
import com.frasinu.service.service_requests.user.DeleteUserRequest;
import com.frasinu.service.service_requests.user.UpdateUserRequest;

/**
 * Created by bjz on 5/7/2017.
 */
public interface IUserService {
    User registerUser(RegisterUserRequest registerUserRequest);

    void deleteUser(DeleteUserRequest deleteUserRequest);

    User updateUser(UpdateUserRequest updateUserRequest);

    void checkLogin(LoginUserRequest loginUserRequest) throws LoginException;
}
