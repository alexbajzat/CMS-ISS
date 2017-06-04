package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.service.ConferenceService;
import com.frasinu.iss.service.service_requests.conference.CreateConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.DeleteConferenceRequest;
import com.frasinu.iss.service.service_requests.conference.UpdateConferenceRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Ericqw on 24.05.2017.
 */
@Controller(value = "AdminConferencesController")
public class AdminConferencesController extends BaseController {
    private ConferenceService conferenceService;
    private @FXML TextField name;
    private @FXML TextField webpage;
    private @FXML TableView<Conference> conferencesTable;
    private ObservableList<Conference> model;
    private @FXML
    TableColumn<Conference,String> colName;
    private @FXML TableColumn<Conference,String> colWebsite;

    @Autowired
    public void setConferenceService(ConferenceService service) {
        this.conferenceService=service;
    }


    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<Conference, String>("name"));
        colWebsite.setCellValueFactory(new PropertyValueFactory<Conference, String>("webpage"));
        model = FXCollections.observableArrayList(conferenceService.getAll());
        conferencesTable.setItems(model);
        conferencesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldConference, newConference) -> {
            if (newConference != null) {
                name.setText(newConference.getName());
                webpage.setText(newConference.getWebpage());
            }
        });



    }
    public void goBack(ActionEvent ac){ FrasinuApplication.changeScreen(Screen.MENUADMIN, getData());}
    public void goToEdition(ActionEvent ac) {
        Conference conference = conferencesTable.getSelectionModel().getSelectedItem();
        if (conference == null)
            showDialog("Please select a conference", "ERROR");
        else {
            getData().put("Conference", conference);
            FrasinuApplication.changeScreen(Screen.ADMINEDITION, getData());
        }
    }

    public void create(ActionEvent ac) {
        if (name.getText().isEmpty() )
            showDialog("Please enter the name of the conference", "ERROR!");
        else {
            conferenceService.addConference(new CreateConferenceRequest(name.getText(), webpage.getText()));
            model = FXCollections.observableArrayList(conferenceService.getAll());
            conferencesTable.setItems(model);
        }
    }

    public void update(ActionEvent ac) {
        Conference conf=conferencesTable.getSelectionModel().getSelectedItem();
        if(conf==null) {
            showDialog("Please select a conference", "ERROR!");
            return;
        }
        if (name.getText().isEmpty()) {
            showDialog("Please enter the name of the conference", "ERROR!");
            return;
        }
        conferenceService.updateConference(new UpdateConferenceRequest(conf.getId(),name.getText(),webpage.getText()));
        model = FXCollections.observableArrayList(conferenceService.getAll());
        conferencesTable.setItems(model);

    }
    public void delete(ActionEvent ac) {
        Conference conf=conferencesTable.getSelectionModel().getSelectedItem();
        if(conf==null) {
            showDialog("Please select a conference", "ERROR!");

        }
        else {
            conferenceService.deleteConference(new DeleteConferenceRequest(conf.getId()));
            model = FXCollections.observableArrayList(conferenceService.getAll());
            conferencesTable.setItems(model);
        }
    }


}
