package com.frasinu.view.controllers;

import com.frasinu.exception.LoginException;
import com.frasinu.exception.RegisterException;
import com.frasinu.service.UserService;
import com.frasinu.service.service_requests.LoginUserRequest;
import com.frasinu.service.service_requests.RegisterUserRequest;
import javafx.event.ActionEvent;
import com.frasinu.view.FrasinuApplication;
import com.frasinu.view.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    public RegisterController(){
        userService=getAppContext().getBean(UserService.class);
    }
    public void goToLogin(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.LOGIN);
    }

    public void register(ActionEvent actionEvent){
//        try {
            userService.registerUser(new RegisterUserRequest(name.getText(),username.getText(), password.getText()));
            showDialog("Registered in with success!", "Great!");
            FrasinuApplication.changeScreen(Screen.CONFERENCES);
//        } catch (RegisterException e) {
//            showDialog(e.getMessage(), "Ooops!");
//        }


    }
}
