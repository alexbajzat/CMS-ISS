package com.frasinu.iss.service.service_requests.user;

/**
 * Created by bjz on 5/7/2017.
 */
public class UpdateUserRequest {
    private final int idOfUserToUpdate;
    private final String newName;
    private final String newUsername;
    private final String newPassword;

    public UpdateUserRequest(int idOfUserToUpdate, String newName, String newUsername, String newPassword) {
        this.idOfUserToUpdate = idOfUserToUpdate;
        this.newName = newName;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
    }

    public int getIdOfUserToUpdate() {
        return idOfUserToUpdate;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
