package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.service_requests.conferenceedition.UpdateEditionRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

/**
 * Created by Betty on 6/6/2017.
 */
@Controller(value = "EditionUpdateController")
public class EditionUpdateController extends BaseController{
    private ConferenceEditionService conferenceEditionService;
    @FXML
    TextArea name,website;
    @FXML
    TextField startDate,endDate;
    @FXML
    DatePicker abstractD,paperD,bidD,evaluationD;
    private ConferenceEdition edition;
    public void goBack(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.STEERING,getData());
    }
    public void update(ActionEvent actionEvent) {
        conferenceEditionService.updateEdition(new UpdateEditionRequest(edition.getId(),edition.getName(),edition.getConferenceStartDate(),edition.getConferenceEndDate(),abstractD.getValue(),paperD.getValue(),bidD.getValue(),evaluationD.getValue(),edition.getConference()));


    }
    public void setData(HashMap<String, Object> data){
        super.setData(data);
        init();
    }
    public void init() {
        edition=(ConferenceEdition)getData().get("edition");
        name.textProperty().setValue(edition.getName());
        startDate.setText(edition.getConferenceStartDate().toString());
        endDate.setText(edition.getConferenceEndDate().toString());
        abstractD.setValue(edition.getAbstractsDeadline());
        paperD.setValue(edition.getFullPapersDeadline());
        bidD.setValue(edition.getBiddingDeadline());
        evaluationD.setValue(edition.getEvaluationDeadline());
        website.textProperty().setValue(edition.getConference().getWebpage());
    }
    @Autowired
    public void setConferenceEditionService(ConferenceEditionService conferenceEditionService) {
        this.conferenceEditionService = conferenceEditionService;
    }
}
