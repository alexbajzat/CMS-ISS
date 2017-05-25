package com.frasinu.iss.view.controllers;

import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Controller;

/**
 * Created by Ericqw on 24.05.2017.
 */
@Controller(value = "AdminConferencesController")
public class AdminConferencesController extends BaseController {
    public void goBack(ActionEvent ac){ FrasinuApplication.changeScreen(Screen.MENUADMIN, getData());}
    public void goToEdition(ActionEvent ac){ FrasinuApplication.changeScreen(Screen.ADMINEDITION, getData());}
}
