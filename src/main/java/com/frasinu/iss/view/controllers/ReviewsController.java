package com.frasinu.iss.view.controllers;

import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Controller;

/**
 * Created by Toshiba on 5/20/2017.
 */
@Controller(value = "ReviewsController")
public class ReviewsController extends BaseController {
    public void back(ActionEvent ae)
    {
        FrasinuApplication.changeScreen(Screen.PCMEMBER,getData());
    }
}
