package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.ConferenceSession;
import com.frasinu.iss.persistance.model.Presentation;
import com.frasinu.iss.service.ConferenceSessionService;
import com.frasinu.iss.service.PresentationService;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.presentation.FindByConferenceSessionRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import com.mysql.cj.api.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ericqw on 24.05.2017.
 */
@Controller(value = "PresentaionScheduleController")
public class PresentaionScheduleController extends BaseController {
    @FXML
    private ComboBox<ConferenceSession> sessionCombo;
    @FXML
    private ComboBox<String> hourCombo;

    private ConferenceSessionService conferenceSessionService;
    private PresentationService presentationService;

    @Autowired
    public void setConferenceSessionService(ConferenceSessionService conferenceSessionService) {

        this.conferenceSessionService=conferenceSessionService;
    }

    @Autowired
    public void setPresentationService(PresentationService presentationService) {

        this.presentationService=presentationService;

    }

    @Override
    public void setData(HashMap<String, Object> data){
        super.setData(data);
        init();
    }

    public void init() {
        List<ConferenceSession> allConfSessions=conferenceSessionService.getAll();
        sessionCombo.getItems().addAll(allConfSessions);
        sessionCombo.getSelectionModel().selectFirst();

        sessionCombo.valueProperty().addListener(new ChangeListener<ConferenceSession>() {
            @Override
            public void changed(ObservableValue<? extends ConferenceSession> observable,
                                ConferenceSession oldValue, ConferenceSession newValue) {
                if(newValue.getPeriodOfDay().equals("AM")) {
                    List<String> allHours = Arrays.asList("8:00", "9:00", "10:00","11:00","12:00");
                    allHours.removeIf((String h) ->
                    {
                        List<Presentation> presentations=presentationService.findByConferenceSessionId(new FindByConferenceSessionRequest(newValue.getId()));
                        for (Presentation pres:presentations) {
                            if(pres.getTime().toString().equals(h))
                                return true;
                        }
                        return false;
                    });
                    allHours.removeIf((String h) -> h.equals(getData().get("idAuthor")));
                    hourCombo.getItems().clear();
                    hourCombo.getItems().addAll(allHours);
                    hourCombo.getSelectionModel().selectFirst();
                }
                else{
                    List<String> allHours = Arrays.asList("13:00", "14:00", "15:00","16:00","17:00");
                    hourCombo.getItems().clear();
                    hourCombo.getItems().addAll(allHours);
                    hourCombo.getSelectionModel().selectFirst();
                }

            }
        });
    }

    public void back(ActionEvent actionEvent){
        FrasinuApplication.changeScreen(Screen.AUTHOR,getData());
    }
}
