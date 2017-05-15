package com.frasinu.view.controllers;

import com.frasinu.service.UserService;
import javafx.event.ActionEvent;
import com.frasinu.view.FrasinuApplication;
import com.frasinu.view.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Paul on 5/9/17.
 */

@Controller
public class LoginController extends BaseController {
    private UserService userService;

    // autowired with setter because FXML`s controllers must have default no-args constructor
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void goToRegister(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.REGISTER);
    }
}
