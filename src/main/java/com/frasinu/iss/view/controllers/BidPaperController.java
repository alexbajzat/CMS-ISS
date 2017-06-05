package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.*;
import com.frasinu.iss.service.BiddedProposalService;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.ReviewerService;
import com.frasinu.iss.service.service_requests.biddedproposal.AddBiddedProposalRequest;
import com.frasinu.iss.service.service_requests.proposal.FindByConferenceEdition;
import com.frasinu.iss.service.service_requests.reviewer.FindReviewerByIdRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Ericqw on 20.05.2017.
 */
@Controller(value = "BidPaperController")
public class BidPaperController extends BaseController {
    @FXML
    private ComboBox<Bid> bidTypesComboBox;

    @Autowired
    private ProposalService proposalService;
    @Autowired
    private ReviewerService reviewerService;
    @Autowired
    private BiddedProposalService biddedProposalService;

    @FXML
    private TableView proposalsTableView;
    @FXML
    private TableColumn<Proposal, String> nameColumn;
    @FXML
    private TableColumn<Proposal, String> authorColumn;
    private Integer conferenceId;

    private void init() {
        ObservableList<Bid> model = FXCollections.observableList(Stream.of(Bid.values())
                .collect(Collectors.toList()));
        bidTypesComboBox.setItems(model);

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
        conferenceId = (Integer) getData().get("idEdition");
        fillProposalsTable(proposalService.findByConferenceId(new FindByConferenceEdition(conferenceId)));
    }

    @Override
    public void setData(HashMap<String, Object> data) {
        super.setData(data);
        init();
    }

    private void fillProposalsTable(List<Proposal> proposals) {
        ObservableList<Proposal> model = FXCollections.observableArrayList(proposals);
        proposalsTableView.setItems(model);
    }

    public void bid() {
        Proposal proposal = (Proposal) proposalsTableView.getSelectionModel().getSelectedItem();
        Integer userId = (Integer) getData().get("idUser");
        Reviewer reviewer = reviewerService.findById(new FindReviewerByIdRequest(userId));
        Bid bid = bidTypesComboBox.getValue();

        if (bid == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Transaction informations");
            alert.setHeaderText(null);
            alert.setContentText("Please select a bid type!");

            alert.showAndWait();
        } else {
            biddedProposalService.add(new AddBiddedProposalRequest(reviewer, proposal, bid));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Transaction informations");
            alert.setHeaderText(null);
            alert.setContentText("Your bid has been succesfuly placed!");

            alert.showAndWait();
        }
    }

    public void back() {
        FrasinuApplication.changeScreen(Screen.PCMEMBER, getData());
    }

}
