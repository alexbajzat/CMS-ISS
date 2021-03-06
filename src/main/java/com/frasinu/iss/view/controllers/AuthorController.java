package com.frasinu.iss.view.controllers;


import com.frasinu.iss.persistance.model.*;
import com.frasinu.iss.service.*;
import com.frasinu.iss.service.service_requests.author.FindProposalsRequest;
import com.frasinu.iss.service.service_requests.author.UpdateAuthorRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindByUserAndEditionIdRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindByUserAndConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import com.frasinu.iss.service.service_requests.user.UpdateUserRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.stream.Collectors;


/**
 * Created by Ericqw on 20.05.2017.
 */
@Controller(value = "AuthorController")
public class AuthorController extends BaseController {
    @FXML
    private TextField name, email, affiliation;

    @FXML
    private TableColumn<Proposal, String> titleColumn;

    @FXML
    private TableColumn<Proposal, String> topicsColumn;

    @FXML
    private TableView<Proposal> uploadedProposalsTableView;

    @FXML
    TableColumn<Proposal, String> topics = new TableColumn<>();


    private AuthorService authorService;
    private UserService userService;
    private ReviewerService reviewerService;
    private SteeringCommitteeMemberService steeringCommitteeMemberService;

    @Autowired
    public void setSteeringCommitteeMemberService(SteeringCommitteeMemberService steeringCommitteeMemberService) {

        this.steeringCommitteeMemberService=steeringCommitteeMemberService;
    }

    @Autowired
    public void setReviewerService(ReviewerService reviewerService) {
        this.reviewerService=reviewerService;
    }
    private ObservableList<Proposal> model;
    private Integer authorId;
    private ProposalService proposalService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProposalService(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    public void seeSchedule(ActionEvent ac) {
        FrasinuApplication.changeScreen(Screen.SCHEDULE, getData());
    }

    public void seeConferenceInfo(ActionEvent ac) {
        FrasinuApplication.changeScreen(Screen.CONFERENCEINFO, getData());
    }

    public void goToPaper(ActionEvent ac) {
        FrasinuApplication.changeScreen(Screen.PAPER, getData());
    }

    public void updatePaper(ActionEvent actionEvent){
        int index=uploadedProposalsTableView.getSelectionModel().getSelectedIndex();
        if (index<0) {
            showDialog("You have to select a paper first!", "Ooops!");
        }
        else {
            HashMap<String, Object> map = getData();
            map.put("idProposal", uploadedProposalsTableView.getSelectionModel().getSelectedItem().getId());
            FrasinuApplication.changeScreen(Screen.PAPERUPDATE, map);
        }
    }

    public void goToConferences(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.CONFERENCES,getData());
    }
    public void goToReviews(ActionEvent actionEvent){
        Proposal proposal = uploadedProposalsTableView.getSelectionModel().getSelectedItem();
        if (proposal != null){
            int idPaper =  proposal.getId();
            getData().put("idPaper", idPaper);
            FrasinuApplication.changeScreen(Screen.PAPERREVIEWS, getData());
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ooops!");
            alert.setHeaderText(null);
            alert.setContentText("Select a paper first!");

            alert.showAndWait();
        }

    }
    public void goToPresentation(ActionEvent actionEvent){
        int index=uploadedProposalsTableView.getSelectionModel().getSelectedIndex();
        if (index<0) {
            showDialog("You have to select a paper first!", "Ooops!");
        }
        else {
            HashMap<String, Object> map = getData();
            map.put("idProposal", uploadedProposalsTableView.getSelectionModel().getSelectedItem().getId());
            FrasinuApplication.changeScreen(Screen.PAPERPRESENTATION, map);
        }
    }

    @Override
    public void setData(HashMap<String, Object> data) {
        super.setData(data);
        init();
    }

    public void init() {
        int idAuthor = (int) getData().get("idAuthor");
        int idUser = (int) getData().get("idUser");
        User user = userService.findById(new FindByIdRequest(idUser));
        Author author = authorService.findById(new FindByIdRequest(idAuthor));
        if (user.getName() != null)
            name.setText(user.getName());
        if (author.getEmail() != null)
            email.setText(author.getEmail());
        if (author.getAffiliation() != null)
            affiliation.setText(author.getAffiliation());
        authorId = (Integer) getData().get("idAuthor");
        model = FXCollections.observableList(authorService.findProposals(new FindProposalsRequest(authorId)));
        uploadedProposalsTableView.setItems(model);
        setCellValueFactoryTopics();

    }

    public void update() {
        int idAuthor = (int) getData().get("idAuthor");
        int idUser = (int) getData().get("idUser");
        User user = userService.findById(new FindByIdRequest(idUser));
        Author author = authorService.findById(new FindByIdRequest(idAuthor));
        User newUser = User.builder().setId(user.getId())
                .setName(name.getText())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword()).build();
        Author newAuthor = Author.builder().setId(author.getId())
                .setEmail(email.getText())
                .setAffiliation(affiliation.getText())
                .setUser(author.getUser())
                .setConferenceEdition(author.getConferenceEdition()).build();
        try {
            userService.updateUserPasswordEncoded(new UpdateUserRequest(newUser.getId(), newUser.getName(), newUser.getUsername(), newUser.getPassword()));
        } catch (ValidationException e) {
            showDialog(e.getMessage(), "Ooops!");
        }
        authorService.updateAuthor(new UpdateAuthorRequest(newAuthor.getId(),newAuthor.getAffiliation(),newAuthor.getEmail(),newAuthor.getUser(),newAuthor.getConferenceEdition()));
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

    public void setCellValueFactoryTopics(){

        topics.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTopics().stream().map(Object::toString)
                .collect(Collectors.joining(", "))));
    }

}
