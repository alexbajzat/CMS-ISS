package com.frasinu.iss.view.controllers;


import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.UserService;
import com.frasinu.iss.service.service_requests.author.FindUserIdRequest;
import com.frasinu.iss.service.service_requests.author.UpdateAuthorRequest;
import com.frasinu.iss.service.service_requests.proposal.FindForAuthorRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import com.frasinu.iss.service.service_requests.user.UpdateUserRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.ValidationException;
import java.util.HashMap;


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
    private TableView uploadedProposalsTableView;

    @FXML
    private Button viewUploadedPapersButton;

    private AuthorService authorService;
    private UserService userService;
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
        uploadedProposalsTableView.setVisible(false);
        authorId = (Integer) getData().get("idAuthor");
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
        authorService.updateUser(new UpdateAuthorRequest(newAuthor.getId(), newAuthor.getAffiliation(), newAuthor.getEmail(), newAuthor.getUser(), newAuthor.getConferenceEdition()));
    }

    public void goToPCMember(ActionEvent ac) {
        FrasinuApplication.changeScreen(Screen.PCMEMBER, getData());
    }

    public void fillProposalsTable() {
        if (uploadedProposalsTableView.isVisible()) {
            uploadedProposalsTableView.setVisible(false);
        } else {
            model = FXCollections.observableList(proposalService.findForAuthor(new FindForAuthorRequest(authorId)));
            uploadedProposalsTableView.setItems(model);
            uploadedProposalsTableView.setVisible(true);
        }
    }

    public void goToSteeringCom(ActionEvent ac) {
        FrasinuApplication.changeScreen(Screen.STEERING, getData());
    }
}
