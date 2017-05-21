package com.frasinu.iss.view.controllers;

import com.frasinu.iss.exception.LoginException;
import com.frasinu.iss.service.UserService;
import com.frasinu.iss.service.service_requests.user.FindByUsernameRequest;
import com.frasinu.iss.service.service_requests.user.LoginUserRequest;
import javafx.event.ActionEvent;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paul on 5/9/17.
 */

@Controller(value = "LoginController")
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
        HashMap<String,Object> map=new HashMap<>();
        map.put("idUser",userService.findByUsername(new FindByUsernameRequest(username.getText())).getId());
        FrasinuApplication.changeScreen(Screen.CONFERENCES,map);
    } catch (LoginException e) {
        showDialog(e.getMessage(), "Ooops!");
    }
    }
}
