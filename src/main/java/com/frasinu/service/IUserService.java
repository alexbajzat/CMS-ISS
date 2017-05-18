package com.frasinu.service;

import com.frasinu.exception.LoginException;
import com.frasinu.model.User;
import com.frasinu.service.service_requests.LoginUserRequest;
import com.frasinu.service.service_requests.RegisterUserRequest;
import com.frasinu.service.service_requests.DeleteUserRequest;
import com.frasinu.service.service_requests.UpdateUserRequest;

/**
 * Created by bjz on 5/7/2017.
 */
public interface IUserService extends IService{
    User registerUser(RegisterUserRequest registerUserRequest);

    void deleteUser(DeleteUserRequest deleteUserRequest);

    User updateUser(UpdateUserRequest updateUserRequest);

    void checkLogin(LoginUserRequest loginUserRequest) throws LoginException;
}
