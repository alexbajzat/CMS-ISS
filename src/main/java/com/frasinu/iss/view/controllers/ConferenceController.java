package com.frasinu.iss.view.controllers;

import com.frasinu.iss.service.ConferenceService;
import com.frasinu.iss.view.Screen;
import com.frasinu.iss.view.FrasinuApplication;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Ericqw on 15.05.2017.
 */
@Controller

public class ConferenceController extends BaseController{
    private ConferenceService conferencesService;

    @Autowired
    public void setConferenceService(ConferenceService conferencesService) {
        this.conferencesService=conferencesService;
    }

    public void goToConferences(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.CONFERENCES);
    }

    public void goToAttend(ActionEvent actionEvent){


        FrasinuApplication.createScreen(Screen.ATTEND);

    }
}
