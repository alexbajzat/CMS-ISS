package com.frasinu.view.controllers;

import com.frasinu.exception.RegisterException;
import com.frasinu.service.UserService;
import com.frasinu.service.service_requests.user.RegisterUserRequest;
import javafx.event.ActionEvent;
import com.frasinu.view.FrasinuApplication;
import com.frasinu.view.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Paul on 5/9/17.
 */
public class RegisterController extends BaseController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    PasswordField password1;
    @FXML
    TextField name;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void goToLogin(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.LOGIN);
    }

    public void register(ActionEvent actionEvent){
        try {
            if(password.getText().equals(password1.getText())){
                userService.registerUser(new RegisterUserRequest(name.getText(),username.getText(), password.getText()));
                showDialog("Registered in with success!", "Great!");
                FrasinuApplication.changeScreen(Screen.CONFERENCES);
            }
            else
                throw new RegisterException("Retyped password different from the original password");

        } catch (RegisterException e) {
            showDialog(e.getMessage(), "Ooops!");
        }

    }
}
