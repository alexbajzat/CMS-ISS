package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.ConferenceService;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Musafir on 5/15/2017.
 */
@Controller(value = "ConferencesController")
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


    public void init(){

    }
}
