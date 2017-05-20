package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.ConferenceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Musafir on 5/15/2017.
 */
@Controller(value = "ConferencesController")
public class ConferencesController extends BaseController implements Initializable {
    ObservableList<Conference> model;
    ObservableList<ConferenceEdition> model1;


    @FXML
    TableView conferences;
    @FXML
    TableView conferenceEditions;
    private ConferenceService conferencesService;


    @Autowired
    public void setConferencesService(ConferenceService conferencesService) {
        this.conferencesService=conferencesService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.model= FXCollections.observableArrayList((List<Conference>)conferencesService.getAll());
        conferences.setItems(model);

        conferences.setRowFactory(tv -> {
            TableRow<Conference> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty()) {

                    Conference clickedRow = row.getItem();
                    this.model1= FXCollections.observableArrayList((List<ConferenceEdition>)conferencesService.findConferenceEditionsByConference(clickedRow));
                    conferenceEditions.setItems(model1);

                }
            });
            return row ;
        });
    }
}
