package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.service_requests.conferenceedition.CreateEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.DeleteEditionRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Ericqw on 24.05.2017.
 */
@Controller(value = "AdminEditionsController")
public class AdminEditionsController extends BaseController {

    public void goBack(ActionEvent ac){ FrasinuApplication.changeScreen(Screen.ADMINCONFERENCES, getData());}
    @FXML
    ListView <ConferenceEdition> listEditions;
    private ConferenceEditionService conferenceEditionService;
    @FXML TextField name;
    @FXML
    DatePicker biddingD,startDate,endDate,abstractD,evaluationD,papersD;

    @Autowired
    public void setConferenceEditionService(ConferenceEditionService service){
        this.conferenceEditionService=service;
    }
    private Conference conference;
    @FXML
    void initialize(){
        //this.conference=(Conference)getData().get("Conference");
        List<ConferenceEdition> editions=conferenceEditionService.getAll();
        ObservableList<ConferenceEdition> items = FXCollections.observableList(editions);
        listEditions.setItems(items);

    }
    public void delete(ActionEvent ac) {
        ConferenceEdition edition=listEditions.getSelectionModel().getSelectedItem();
        if(edition==null)
            showDialog("Please select an edition","ERROR");
        else{
            conferenceEditionService.deleteEdition(new DeleteEditionRequest(edition));
        }

    }

    public void add(ActionEvent ac) {

        conferenceEditionService.addEdition(new CreateEditionRequest(name.getText(),startDate.getValue(),endDate.getValue(),abstractD.getValue(),papersD.getValue(),biddingD.getValue(),evaluationD.getValue(),conference));
    }


}
