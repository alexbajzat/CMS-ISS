package com.frasinu.iss.view.controllers;

import com.frasinu.iss.exception.LoginException;
import com.frasinu.iss.service.UserService;
import com.frasinu.iss.service.service_requests.user.FindByUsernameRequest;
import com.frasinu.iss.service.service_requests.user.LoginUserRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

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
        if(username.getText().equals("admin"))
           FrasinuApplication.changeScreen(Screen.MENUADMIN,map);
        else
           FrasinuApplication.changeScreen(Screen.CONFERENCES,map);
    } catch (LoginException e) {
        showDialog(e.getMessage(), "Ooops!");
    }
    }
    @FXML
    public void initialize(){
        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                   login(new ActionEvent());
                }
            }
        });

    }
}
