package com.frasinu.iss.view.controllers;

import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

/**
 * Created by Ericqw on 24.05.2017.
 */
@Controller(value = "PaperUpdateController")
public class PaperUpdateController extends BaseController {
    public void back(ActionEvent actionEvent){
        FrasinuApplication.changeScreen(Screen.AUTHOR,getData());
    }
}
