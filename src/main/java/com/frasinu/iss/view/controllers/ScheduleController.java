package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.*;
import com.frasinu.iss.service.*;
import com.frasinu.iss.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.listener.CreateListenerRequest;
import com.frasinu.iss.service.service_requests.listener.FindByUserAndSessionIdRequest;
import com.frasinu.iss.service.service_requests.presentation.FindByConferenceSessionRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindByUserAndEditionIdRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindByUserAndConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import com.frasinu.iss.service.service_requests.user.FindIfUserIsAuthorRequest;
import com.frasinu.iss.view.Screen;
import com.frasinu.iss.view.FrasinuApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ericqw on 15.05.2017.
 */
@Controller(value = "ScheduleController")
public class ScheduleController extends BaseController{
    private ConferenceService conferencesService;
    private ConferenceEditionService conferenceEditionService;
    private PresentationService presentationService;
    private UserService userService;
    private AuthorService authorService;
    private ReviewerService reviewerService;
    private SteeringCommitteeMemberService steeringCommitteeMemberService;
    private ConferenceSessionService conferenceSessionService;
    private ListenerService listenerService;


    ObservableList<ConferenceSession> modelConferenceSessions;
    ObservableList<Presentation> modelPresentations;


    @FXML
    TableView<ConferenceSession> conferenceSessions;
    @FXML
    TableView<Presentation> presentations;

    @FXML
    private TableColumn<Presentation, String> presentationTitleColumn;

    @FXML
    private TableColumn<Presentation, String> presentationSpeakerColumn;

    @Autowired
    public void setConferenceSessionService(ConferenceSessionService conferenceSessionService) {

        this.conferenceSessionService=conferenceSessionService;
    }

    @Autowired
    public void setListenerService(ListenerService listenerService) {
            this.listenerService=listenerService;
    }

    @Autowired
    public void setPresentationService(PresentationService presentationService) {

        this.presentationService=presentationService;
    }

    @Autowired
    public void setSteeringCommitteeMemberService(SteeringCommitteeMemberService steeringCommitteeMemberService) {

        this.steeringCommitteeMemberService=steeringCommitteeMemberService;
    }

    @Autowired
    public void setReviewerService(ReviewerService reviewerService) {
        this.reviewerService=reviewerService;
    }

    @Autowired
    public void setConferenceEditionService(ConferenceEditionService conferenceEditionService) {
        this.conferenceEditionService = conferenceEditionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService=userService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService=authorService;
    }

    @Autowired
    public void setConferenceService(ConferenceService conferencesService) {
        this.conferencesService=conferencesService;
    }


    @Override
    public void setData(HashMap<String, Object> data){
        super.setData(data);
        init();
    }

    public void init() {
        this.setCellValueFactoryPresentation();
        this.modelConferenceSessions= FXCollections.observableArrayList(conferenceSessionService.
                findByConferenceEditionId(new FindByConferenceEditionIdRequest((int)getData().get("idEdition"))));
        conferenceSessions.setItems(modelConferenceSessions);
        conferenceSessions.setRowFactory(tv -> {
            TableRow<ConferenceSession> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty()) {

                    ConferenceSession clickedConferenceSession = row.getItem();
                    this.modelPresentations= FXCollections.observableArrayList(presentationService.findByConferenceSessionId
                            (new FindByConferenceSessionRequest(clickedConferenceSession.getId())));
                    presentations.setItems(modelPresentations);

                }
            });
            return row ;
        });
    }


    public void goToConferences(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.CONFERENCES,getData());
    }

    public void goToAttend(ActionEvent actionEvent){
        ConferenceSession conferenceSession=conferenceSessions.getSelectionModel().getSelectedItem();
        if (conferenceSession==null) {
            showDialog("You have to select a conference session first!", "Ooops!");
        }
        else {
            List<Listener> listen=listenerService.findByConferenceSessionIAndUser(new FindByUserAndSessionIdRequest((int)getData().get("idUser"),conferenceSession.getId()));
            if(listen.size()>0){
                showDialog("You are already registered as a listener to this session!", "Ooops!");

            }
            else{
                listenerService.addListener(new CreateListenerRequest(conferenceSession,userService.findById(new FindByIdRequest((int)getData().get("idUser")))));
                showDialog("You were registered as a listener!", "Ooops!");

            }
        }
    }
    public void seeConferenceInfo(ActionEvent ac){
        FrasinuApplication.changeScreen(Screen.CONFERENCEINFO,getData());
    }

    public void goToAuthor(ActionEvent ac){
        Author author=userService.findIfUserIsAuthor(new FindIfUserIsAuthorRequest((int)getData().get("idUser"),(int)getData().get("idEdition")));
        if(author==null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You are not registered as an author");
            alert.setContentText("Do you want to register?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                showDialog("You are now registered as an author. Please complete your personal info on the left side to " +
                        "complete the registration.", "Info!");
                author=authorService.addAuthor(new CreateAuthorRequest("", "", userService.findById(new FindByIdRequest((int) getData().get("idUser"))),
                        conferenceEditionService.findByConferenceEditionId(new FindByConferenceEditionIdRequest((int) getData().get("idEdition")))));
            } else {
                return;
            }
        }
        HashMap<String, Object> map = getData();
        map.put("idAuthor", author.getId());
        FrasinuApplication.changeScreen(Screen.AUTHOR, getData());
    }
    public void goToPCMember(ActionEvent ac) {
        Reviewer reviewer = reviewerService.findByUserAndEditionId(new FindByUserAndEditionIdRequest((int) getData().get("idUser"), (int) getData().get("idEdition")));
        if (reviewer == null) {
            showDialog("You are not part of the Program Committee Members", "Ooops!");
            return;
        } else {
            if (reviewer.getEmail() == null && reviewer.getWebpage() == null && reviewer.getAffiliation() == null)
                showDialog("We are glad that you accepted to be a Program Committee Member this year. Please complete your personal info on the left side to " +
                        "complete the registration.", "Info!");

            HashMap<String, Object> map = getData();
            map.put("idReviewer", reviewer.getId());
            FrasinuApplication.changeScreen(Screen.PCMEMBER, getData());
        }
    }

    public void goToSteeringCom(ActionEvent ac) {
        SteeringCommitteeMember steeringCommitteeMember = steeringCommitteeMemberService.findByUserAndConferenceEditionId(new FindByUserAndConferenceEditionIdRequest((int) getData().get("idUser"), (int) getData().get("idEdition")));
        if (steeringCommitteeMember == null) {
            showDialog("You are not part of the Steering Committee Members", "Ooops!");
            return;
        } else {
            HashMap<String, Object> map = getData();
            map.put("idSteeringCommitteeMember", steeringCommitteeMember.getId());
            FrasinuApplication.changeScreen(Screen.STEERING, getData());
        }
    }

    public void setCellValueFactoryPresentation(){

        presentationTitleColumn.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getProposal().getTitle()));
        presentationSpeakerColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getAuthor().getUser().getName()));
    }
}
