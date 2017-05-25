package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.*;
import com.frasinu.iss.service.*;
import com.frasinu.iss.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.iss.service.service_requests.reviewer.CreateReviewerRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindByUserAndEditionIdRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.CreateSteeringRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindByUserAndConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.UpdateSteeringRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Ericqw on 24.05.2017.
 */
@Controller(value = "AdminUsersController")
public class AdminUsersController extends BaseController {
    @FXML
    private CheckBox author;
    @FXML
    private CheckBox steeringMember;
    @FXML
    private CheckBox pcMember;
    @FXML
    private ComboBox<Conference> conferencesCombo;
    @FXML
    private ComboBox<ConferenceEdition> editionsCombo;
    @FXML
    private ComboBox<String> rankCombo;
    private @FXML TableColumn<User,String> usernameCol;
    private @FXML TableColumn<User,String> nameCol;
    private @FXML TableView<User> table;
    private ObservableList<User> model;


    private ConferenceService conferenceService;
    private ConferenceEditionService editionService;
    private UserService userService;
    private AuthorService authorService;
    private ReviewerService reviewerService;
    private SteeringCommitteeMemberService steeringService;
    @FXML
    public void initialize() {
        rankCombo.getItems().addAll("","Chair","Co-chair");
        rankCombo.editableProperty().setValue(false);
        table.getSelectionModel().selectFirst();
        List<Conference> allConf=conferenceService.getAll();
        conferencesCombo.getItems().addAll(allConf);
        conferencesCombo.getSelectionModel().selectFirst();
        Conference c=(Conference) conferencesCombo.getSelectionModel().getSelectedItem();
        List<ConferenceEdition> allEditions=editionService.findEditionsFotConference(c.getId());
        editionsCombo.getItems().addAll(allEditions);
        editionsCombo.getSelectionModel().selectFirst();

        steeringMember.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (steeringMember.isSelected()) {
                    rankCombo.setVisible(true);
                    rankCombo.getSelectionModel().selectFirst();
                }
                else
                    rankCombo.setVisible(false);
            }
        });

        conferencesCombo.valueProperty().addListener(new ChangeListener<Conference>() {
            @Override
            public void changed(ObservableValue<? extends Conference> observable,
                                Conference oldValue, Conference newValue) {
                List<ConferenceEdition> allEditions=editionService.findEditionsFotConference(newValue.getId());
                editionsCombo.getItems().clear();
                editionsCombo.getItems().addAll(allEditions);
                editionsCombo.getSelectionModel().selectFirst();

            }
        });

        editionsCombo.valueProperty().addListener(new ChangeListener<ConferenceEdition>() {
            @Override
            public void changed(ObservableValue<? extends ConferenceEdition> observable,
                                ConferenceEdition oldValue, ConferenceEdition newValue) {
                User user=table.getSelectionModel().getSelectedItem();

                refreshRights(user);
            }
        });;


        List<User> users=userService.getAll();

        usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        model = FXCollections.observableArrayList(users);
        table.setItems(model);


        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                refreshRights(newSelection);
            }
        });


    }

    private void refreshRights(User user){
        author.setSelected(false);
        pcMember.setSelected(false);
        steeringMember.setSelected(false);
        rankCombo.setVisible(false);
        if(user!=null) {
            ConferenceEdition ed = (ConferenceEdition) editionsCombo.getSelectionModel().getSelectedItem();
            Author a = authorService.findByUserIdEditionId(new FindByUserAndEditionIdRequest(user.getId(), ed.getId()));
            Reviewer pc = reviewerService.findByUserAndEditionId(new FindByUserAndEditionIdRequest(user.getId(), ed.getId()));
            SteeringCommitteeMember st=steeringService.findByUserAndConferenceEditionId(new FindByUserAndConferenceEditionIdRequest(user.getId(), ed.getId()));
            if (a != null)
                author.setSelected(true);
            if (pc != null)
                pcMember.setSelected(true);
            if (st != null) {
                steeringMember.setSelected(true);
                rankCombo.setVisible(true);
                if (st.getRank().equals("Chair"))
                    rankCombo.getSelectionModel().select("Chair");
                else {
                    if (st.getRank().equals("Co-Chair"))
                        rankCombo.getSelectionModel().select("Co-Chair");
                    else
                        rankCombo.getSelectionModel().selectLast();
                }

            }



        }


    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }
    @Autowired
    public void setConferenceEditionService(ConferenceEditionService editionService) {
        this.editionService=editionService;
    }
    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService=authorService;
    }
    @Autowired
    public void setReviewerService(ReviewerService reviewerService) {
        this.reviewerService=reviewerService;
    }
    @Autowired
    public void setSteeringService(SteeringCommitteeMemberService sService) {
        this.steeringService=sService;
    }



    public void goToMenu(ActionEvent ac){ FrasinuApplication.changeScreen(Screen.MENUADMIN, getData());}
    public void update(ActionEvent ac){
        User user=table.getSelectionModel().getSelectedItem();
        if (user==null) {
            showDialog("Please select a user", "ERROR");
            return;
        }
        ConferenceEdition ed = (ConferenceEdition) editionsCombo.getSelectionModel().getSelectedItem();
        Author a = authorService.findByUserIdEditionId(new FindByUserAndEditionIdRequest(user.getId(), ed.getId()));
        Reviewer pc = reviewerService.findByUserAndEditionId(new FindByUserAndEditionIdRequest(user.getId(), ed.getId()));
        SteeringCommitteeMember st=steeringService.findByUserAndConferenceEditionId(new FindByUserAndConferenceEditionIdRequest(user.getId(), ed.getId()));



        if (a==null && author.isSelected())
           authorService.addAuthor(new CreateAuthorRequest("","",user,ed));
        if (pc==null && pcMember.isSelected())
            reviewerService.addReviewer(new CreateReviewerRequest("","","",user,ed));
        if (a!=null && !author.isSelected())
            authorService.deleteAuthor(a.getId());
        if (pc!=null && !pcMember.isSelected())
            reviewerService.deleteReviewer(pc.getId());
        String rank=rankCombo.getSelectionModel().getSelectedItem();
        if (st==null && steeringMember.isSelected()){
            steeringService.addSteering(new CreateSteeringRequest(rank,ed,user));
        }
        if (st!=null && steeringMember.isSelected() && !st.getRank().equals(rank)){
            steeringService.updateSteering(new UpdateSteeringRequest(st.getId(),rank,ed,user));
            System.out.println(rank+"  "+st.getRank());
        }


        if (st!=null && !steeringMember.isSelected())
            steeringService.deleteSteering(st.getId());

    }


}
