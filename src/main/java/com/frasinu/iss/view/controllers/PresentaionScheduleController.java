package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.*;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ConferenceSessionService;
import com.frasinu.iss.service.PresentationService;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.conferencesession.FindBySessionIdRequest;
import com.frasinu.iss.service.service_requests.presentation.CreatePresentationRequest;
import com.frasinu.iss.service.service_requests.presentation.FindByConferenceSessionRequest;
import com.frasinu.iss.service.service_requests.proposal.FindByPaperIdRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private AuthorService authorService;
    private ProposalService proposalService;

    @Autowired
    public void setProposalService(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {

        this.authorService=authorService;
    }
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
                    List<String> allHours = Stream.of("08:00:00,09:00:00,10:00:00,11:00:00,12:00:00".split(","))
                            .collect(Collectors.toList());
                    allHours.removeIf((String h) ->
                    {
                        List<Presentation> presentations=presentationService.findByConferenceSessionId(new FindByConferenceSessionRequest(newValue.getId()));
                        for (Presentation pres:presentations) {
                            String tm=pres.getTime().toString();
                            if(tm.equals(h)) {
                                return true;
                            }
                        }
                        return false;
                    });
                    hourCombo.getItems().clear();
                    hourCombo.getItems().addAll(allHours);
                    hourCombo.getSelectionModel().selectFirst();
                }
                else{
                    List<String> allHours = Stream.of("13:00:00,14:00:00,15:00:00,16:00:00,17:00:00".split(","))
                            .collect(Collectors.toList());
                    allHours.removeIf((String h) ->
                    {
                        List<Presentation> presentations=presentationService.findByConferenceSessionId(new FindByConferenceSessionRequest(newValue.getId()));
                        for (Presentation pres:presentations) {
                            String tm=pres.getTime().toString();
                            if(tm.equals(h)) {
                                return true;
                            }
                        }
                        return false;
                    });
                    hourCombo.getItems().clear();
                    hourCombo.getItems().addAll(allHours);
                    hourCombo.getSelectionModel().selectFirst();
                }

            }
        });
    }

    public void present(ActionEvent actionEvent){
        ConferenceSession conferenceSession=sessionCombo.getSelectionModel().getSelectedItem();
        if (conferenceSession==null) {
            showDialog("Please select a conference session", "ERROR");
            return;
        }
        String hour=hourCombo.getSelectionModel().getSelectedItem();
        if (hour==null) {
            showDialog("Please select a hour", "ERROR");
            return;
        }
        Integer authorId = (Integer) getData().get("idAuthor");
        Author author = authorService
                .findById(new FindByIdRequest(authorId));
        Integer paperId=(Integer) getData().get("idProposal");
       Proposal proposal=proposalService.findById(new FindByPaperIdRequest(paperId));
        presentationService.addPresentation(new CreatePresentationRequest(Time.valueOf(hour),author,conferenceSession,proposal));
        showDialog("Your presentation was registered!", "Great!");
    }

    public void back(ActionEvent actionEvent){
        FrasinuApplication.changeScreen(Screen.AUTHOR,getData());
    }
}
