package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.*;
import com.frasinu.iss.service.*;
import com.frasinu.iss.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.proposal.FindByConferenceEdition;
import com.frasinu.iss.service.service_requests.reviewer.FindReviewerByIdRequest;
import com.frasinu.iss.service.service_requests.reviewer.GetAllReviewedProposalsRequest;
import com.frasinu.iss.service.service_requests.reviewer.UpdateReviewerRequest;
import com.frasinu.iss.service.service_requests.steeringcommitteemember.FindByUserAndConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import com.frasinu.iss.service.service_requests.user.FindIfUserIsAuthorRequest;
import com.frasinu.iss.service.service_requests.user.UpdateUserRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ericqw on 22.05.2017.
 */
@Controller(value = "PCController")
public class PCController extends BaseController {
    private SteeringCommitteeMemberService steeringCommitteeMemberService;
    private ConferenceEditionService conferenceEditionService;
    private UserService userService;
    private AuthorService authorService;
    private ReviewerService reviewerService;
    private ProposalService proposalService;

    private Integer idEdition;
    private Reviewer currentReviewer;
    private List<ReviewedProposal> reviewedProposals;

    @FXML private TextField name, email, affiliation, webpage;
    @FXML
    private TableView<Proposal> papersTableView;
    @FXML
    private TableColumn<Proposal, String> nameColumn;
    @FXML
    private TableColumn<Proposal, String> authorColumn;
    @FXML
    private CheckBox reviewedCheckBox;

    @Autowired
    public void setSteeringCommitteeMemberService(SteeringCommitteeMemberService steeringCommitteeMemberService) {

        this.steeringCommitteeMemberService = steeringCommitteeMemberService;
    }

    @Autowired
    public void setProposalService(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @Autowired
    public void setReviewerService(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }

    @Autowired
    public void setConferenceEditionService(ConferenceEditionService conferenceEditionService) {
        this.conferenceEditionService = conferenceEditionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void setData(HashMap<String, Object> data) {
        super.setData(data);
        init();
    }

    private void init() {
        int idReviewer = (int) getData().get("idReviewer");
        int idUser = (int) getData().get("idUser");
        idEdition = (Integer) getData().get("idEdition");

        User user = userService.findById(new FindByIdRequest(idUser));
        currentReviewer = reviewerService.findById(new FindReviewerByIdRequest(idReviewer));
        reviewedProposals = reviewerService.getReviewdProposals(new GetAllReviewedProposalsRequest(idReviewer));

        if (user.getName() != null)
            name.setText(user.getName());
        if (currentReviewer.getEmail() != null)
            email.setText(currentReviewer.getEmail());
        if (currentReviewer.getAffiliation() != null)
            affiliation.setText(currentReviewer.getAffiliation());
        if (currentReviewer.getWebpage() != null)
            webpage.setText(currentReviewer.getWebpage());

        nameColumn.setCellValueFactory((proposal) -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(proposal.getValue().getTitle());
            return property;
        });

        authorColumn.setCellValueFactory((proposal) -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(proposal.getValue().getAuthors().get(0).getUser().getName());
            return property;
        });

        papersTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    if (newValue != null){
                        for (ReviewedProposal reviewedProposal:
                             reviewedProposals) {
                            if (reviewedProposal.getProposal().getId() == newValue.getId()){
                                reviewedCheckBox.setSelected(true);
                                return;
                            }
                        }
                    }
                    reviewedCheckBox.setSelected(false);
                });

        fillProposalsTable(proposalService.findByConferenceId(new FindByConferenceEdition(idEdition)));
    }

    private void fillProposalsTable(List<Proposal> proposals) {
        ObservableList<Proposal> model = FXCollections.observableArrayList(proposals);
        papersTableView.setItems(model);
    }

    public void update() {
        int idReviewer = (int) getData().get("idReviewer");
        int idUser = (int) getData().get("idUser");
        User user = userService.findById(new FindByIdRequest(idUser));
        Reviewer reviewer = reviewerService.findById(new FindReviewerByIdRequest(idReviewer));
        User newUser = User.builder().setId(user.getId())
                .setName(name.getText())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword()).build();
        Reviewer newReviewer = Reviewer.builder().setId(reviewer.getId())
                .setEmail(email.getText())
                .setAffiliation(affiliation.getText())
                .setWebpage(webpage.getText())
                .setUser(reviewer.getUser())
                .setConferenceEdition(reviewer.getConferenceEdition()).build();
        try {
            userService.updateUserPasswordEncoded(new UpdateUserRequest(newUser.getId(), newUser.getName(), newUser.getUsername(), newUser.getPassword()));
        } catch (ValidationException e) {
            showDialog(e.getMessage(), "Ooops!");
        }
        reviewerService.updateReviewer(new UpdateReviewerRequest(newReviewer.getId(), newReviewer.getAffiliation(), newReviewer.getEmail(), newReviewer.getWebpage(), newReviewer.getUser(), newReviewer.getConferenceEdition()));
    }

    public void seeSchedule(javafx.event.ActionEvent ac) {
        FrasinuApplication.changeScreen(Screen.SCHEDULE, getData());
    }

    public void seeConferenceInfo(javafx.event.ActionEvent ac) {
        FrasinuApplication.changeScreen(Screen.CONFERENCEINFO, getData());
    }

    public void goToAuthor(javafx.event.ActionEvent ac) {

        Author author = userService.findIfUserIsAuthor(new FindIfUserIsAuthorRequest((int) getData().get("idUser"), (int) getData().get("idEdition")));
        if (author == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You are not registered as an author");
            alert.setContentText("Do you want to register?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                showDialog("You are now registered as an author. Please complete your personal info on the left side to " +
                        "complete the registration.", "Info!");
                author = authorService.addAuthor(new CreateAuthorRequest("", "", userService.findById(new FindByIdRequest((int) getData().get("idUser"))),
                        conferenceEditionService.findByConferenceEditionId(new FindByConferenceEditionIdRequest((int) getData().get("idEdition")))));
            } else {
                return;
            }
        }
        HashMap<String, Object> map = getData();
        map.put("idAuthor", author.getId());

        FrasinuApplication.changeScreen(Screen.AUTHOR, getData());
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

    public void goToConferences(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.CONFERENCES, getData());
    }

    public void bidPapers(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.BIDPAPERS, getData());
    }

    public void makeReview(ActionEvent actionEvent) {
        if (reviewedCheckBox.isSelected()){
            int idPaper =  papersTableView.getSelectionModel().getSelectedItem().getId();
            getData().put("idPaper", idPaper);
            FrasinuApplication.changeScreen(Screen.MAKEREVIEW, getData());
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ooops!");
            alert.setHeaderText(null);
            alert.setContentText("You cannot make a review for this paper!");

            alert.showAndWait();
        }
    }

    public void seeReview(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.REVIEWS, getData());
    }

}
