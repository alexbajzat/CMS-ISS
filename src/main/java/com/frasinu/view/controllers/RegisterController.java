package com.frasinu.view.controllers;

import javafx.event.ActionEvent;
import com.frasinu.view.FrasinuApplication;
import com.frasinu.view.Screen;

/**
 * Created by Paul on 5/9/17.
 */
public class RegisterController extends BaseController {
    public void goToLogin(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.LOGIN);
    }
}
