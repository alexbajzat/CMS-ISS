package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.*;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.ConferenceService;
import com.frasinu.iss.service.UserService;
import com.frasinu.iss.service.service_requests.user.FindIfUserIsAuthorRequest;
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
    private @FXML TableColumn<User,String> usernameCol;
    private @FXML TableColumn<User,String> nameCol;
    private @FXML TableView<User> table;
    private ObservableList<User> model;

    private UserService userService;
    private ConferenceService conferenceService;
    private ConferenceEditionService editionService;
    @FXML
    public void initialize() {
        table.getSelectionModel().selectFirst();
        List<Conference> allConf=conferenceService.getAll();
        conferencesCombo.getItems().addAll(allConf);
        conferencesCombo.getSelectionModel().selectFirst();
        Conference c=(Conference) conferencesCombo.getSelectionModel().getSelectedItem();
        List<ConferenceEdition> allEditions=editionService.findEditionsFotConference(c.getId());
        editionsCombo.getItems().addAll(allEditions);
        editionsCombo.getSelectionModel().selectFirst();

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

        if(user!=null) {
            ConferenceEdition ed = (ConferenceEdition) editionsCombo.getSelectionModel().getSelectedItem();
            Author a = userService.findIfUserIsAuthor(new FindIfUserIsAuthorRequest(user.getId(), ed.getId()));
            Reviewer pc = userService.findIfUserIsPC(user.getId(), ed.getId());
            if (a != null)
                author.setSelected(true);
            if (pc != null)
                pcMember.setSelected(true);

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



    public void goToMenu(ActionEvent ac){ FrasinuApplication.changeScreen(Screen.MENUADMIN, getData());}
    public void update(ActionEvent ac){


    }


}
