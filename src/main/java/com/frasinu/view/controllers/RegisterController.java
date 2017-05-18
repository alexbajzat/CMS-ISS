package com.frasinu.view.controllers;

import com.frasinu.exception.ValidateException;
import com.frasinu.service.UserService;
import com.frasinu.service.service_requests.RegisterUserRequest;
import javafx.event.ActionEvent;
import com.frasinu.view.FrasinuApplication;
import com.frasinu.view.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.xml.bind.ValidationException;

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
        try {
            if(password.getText().equals(password1.getText())){
                userService.registerUser(new RegisterUserRequest(name.getText(),username.getText(), password.getText()));
                showDialog("Registered in with success!", "Great!");
                FrasinuApplication.changeScreen(Screen.CONFERENCES);
            }
            else
                throw new ValidateException("Retyped password different from the original password");

        } catch (ValidateException e) {
            showDialog(e.getMessage(), "Ooops!");
        }

    }
}
