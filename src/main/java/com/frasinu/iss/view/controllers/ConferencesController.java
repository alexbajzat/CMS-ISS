package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.ConferenceService;
import com.frasinu.iss.service.service_requests.conference.FindConferenceEditionByConferenceRequest;
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
    ObservableList<Conference> modelConferences;
    ObservableList<ConferenceEdition> modelConferenceEditions;


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
        this.modelConferences= FXCollections.observableArrayList(conferencesService.getAll());
        conferences.setItems(modelConferences);

        conferences.setRowFactory(tv -> {
            TableRow<Conference> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty()) {

                    Conference clickedConference = row.getItem();
                    this.modelConferenceEditions= FXCollections.observableArrayList(conferencesService.findConferenceEditionsByConference
                            (new FindConferenceEditionByConferenceRequest(clickedConference.getId())));
                    conferenceEditions.setItems(modelConferenceEditions);

                }
            });
            return row ;
        });
    }
}
