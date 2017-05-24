package com.frasinu.iss.view.controllers;

import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

/**
 * Created by Ericqw on 24.05.2017.
 */

@Controller(value = "AdminMenuController")
public class AdminMenuController extends BaseController {
    public void goToUsers(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.ADMINUSERS,getData());

    }
    public void goToConferences(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.ADMINCONFERENCES,getData());

    }

    public void goBack(ActionEvent actionEvent) {
        HashMap<String,Object> map=new HashMap<>();
        FrasinuApplication.changeScreen(Screen.LOGIN,getData());

    }
}
