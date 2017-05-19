package com.frasinu.view.controllers;

import com.frasinu.exception.LoginException;
import com.frasinu.service.UserService;
import com.frasinu.service.service_requests.user.LoginUserRequest;
import javafx.event.ActionEvent;
import com.frasinu.view.FrasinuApplication;
import com.frasinu.view.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Paul on 5/9/17.
 */

@Controller
public class LoginController extends BaseController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void goToRegister(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.REGISTER);
    }

    public void login(ActionEvent actionEvent) {
       try {
        userService.checkLogin(new LoginUserRequest(username.getText(), password.getText()));
        showDialog("Logged in with success!", "Great!");
        FrasinuApplication.changeScreen(Screen.CONFERENCES);
    } catch (LoginException e) {
        showDialog(e.getMessage(), "Ooops!");
    }
    }
}
