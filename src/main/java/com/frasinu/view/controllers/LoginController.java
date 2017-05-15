package com.frasinu.view.controllers;

import javafx.event.ActionEvent;
import com.frasinu.view.FrasinuApplication;
import com.frasinu.view.Screen;

/**
 * Created by Paul on 5/9/17.
 */
public class LoginController extends BaseController {
    public void goToRegister(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.REGISTER);
    }
}
