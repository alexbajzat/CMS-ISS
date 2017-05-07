package service;

import exception.InexistentException;
import model.User;
import service.service_requests.AddUserRequest;
import service.service_requests.DeleteUserRequest;
import service.service_requests.UpdateUserRequest;

import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
public interface IUserService {
    User add(AddUserRequest addUserRequest);

    void delete(DeleteUserRequest deleteUserRequest) throws InexistentException;

    User update(UpdateUserRequest updateUserRequest) throws InexistentException;

    List<User> getAll();
}
