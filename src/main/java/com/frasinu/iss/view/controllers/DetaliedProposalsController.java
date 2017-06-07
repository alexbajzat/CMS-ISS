package com.frasinu.iss.view.controllers;

import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Controller;

/**
 * Created by Ericqw on 07.06.2017.
 */
@Controller(value = "DetaliedProposalsController")
public class DetaliedProposalsController extends BaseController {
    public void back(ActionEvent actionEvent){
        FrasinuApplication.changeScreen(Screen.PCMEMBER,getData());
    }
}
