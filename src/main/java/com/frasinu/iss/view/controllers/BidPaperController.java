package com.frasinu.iss.view.controllers;

import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import org.springframework.stereotype.Controller;

/**
 * Created by Ericqw on 20.05.2017.
 */
@Controller(value = "BidPaperController")
public class BidPaperController extends BaseController {
    public void back(){
        FrasinuApplication.changeScreen(Screen.PCMEMBER,getData());
    }
}
