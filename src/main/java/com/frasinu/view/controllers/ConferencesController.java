package com.frasinu.view.controllers;

import com.frasinu.persistance.model.Conference;
import com.frasinu.persistance.model.ConferenceEdition;
import com.frasinu.service.ConferenceService;
import com.frasinu.service.UserService;
import com.frasinu.view.FrasinuApplication;
import com.frasinu.view.Screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Musafir on 5/15/2017.
 */
@Controller
public class ConferencesController extends BaseController {

    @FXML
    TableView<Conference> conferences;
    @FXML
    TableView<ConferenceEdition> conferenceEditions;
    private ConferenceService conferencesService;

    public ConferencesController(){
        init();
    }
    @Autowired
    public void setConferenceService(ConferenceService conferencesService) {
        this.conferencesService=conferencesService;
    }

    public void goToSchedule(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.CONFERENCE);
    }

    public void init(){

    }
}
