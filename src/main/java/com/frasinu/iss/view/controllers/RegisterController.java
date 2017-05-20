package com.frasinu.iss.view.controllers;

import com.frasinu.iss.exception.RegisterException;
import com.frasinu.iss.service.UserService;
import com.frasinu.iss.service.service_requests.user.RegisterUserRequest;
import com.frasinu.iss.view.Screen;
import javafx.event.ActionEvent;
import com.frasinu.iss.view.FrasinuApplication;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Paul on 5/9/17.
 */
@Controller(value = "RegisterController")
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
                FrasinuApplication.changeScreen(Screen.LOGIN);
            }
            else
                throw new RegisterException("Retyped password different from the original password");

        } catch (RegisterException e) {
            showDialog(e.getMessage(), "Ooops!");
        }

    }
}
