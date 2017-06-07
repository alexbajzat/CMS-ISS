package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.BiddedProposal;
import com.frasinu.iss.persistance.model.Result;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.service.BiddedProposalService;
import com.frasinu.iss.service.ReviewerService;
import com.frasinu.iss.service.service_requests.reviewer.AssignPaperToReviewerRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindReviewersByEditionRequest;
import com.frasinu.iss.service.service_requests.reviewer.GetBiddedProposalsForReviewerRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Toshiba on 5/20/2017.
 */
@Controller(value = "BidsController")
public class BidsController extends BaseController {
    @FXML
    private ListView<Reviewer> listReviewers;
    @FXML
    private TableView<BiddedProposal> tablePapers;
    @FXML
    private TextField paperTxt, reviewerTxt, resultTxt;
    @FXML
    private TableColumn<BiddedProposal, String> titleColumn, resultColumn;

    private ReviewerService reviewerService;
    private BiddedProposalService biddedProposalService;
    Reviewer selectedReviewer = null;
    BiddedProposal selectedPaper = null;


    @Autowired
    public void setBiddedProposalService(BiddedProposalService biddedProposalService){
        this.biddedProposalService = biddedProposalService;
    }

    @Autowired
    public void setReviewerService(ReviewerService reviewerService) {
        this.reviewerService=reviewerService;
    }


    public void back(ActionEvent ae)
    {
        FrasinuApplication.changeScreen(Screen.STEERING, getData());
    }

    @Override
    public void setData(HashMap<String, Object> data){
        super.setData(data);
        init();
    }

    private void init() {
        initReviewer();
        initPaper();
    }

    private void initPaper() {
        int idEdition = (int)getData().get("idEdition");

        listReviewers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                tablePapers.getSelectionModel().clearSelection();
            }
            else{
                Reviewer reviewer = newSelection;

                List<BiddedProposal> biddedProposals = biddedProposalService.
                        getAllByReviewer(new GetBiddedProposalsForReviewerRequest(reviewer.getId(), idEdition));

                tablePapers.setItems(FXCollections.observableArrayList(biddedProposals));
            }
        });

        tablePapers.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    selectedPaper = newValue;
                    if (newValue != null){
                        paperTxt.setText(selectedPaper.getProposal().getTitle());
                        resultTxt.setText(selectedPaper.getResult() );
                    }
                    else {
                        paperTxt.setText(null);
                        resultTxt.setText(null);
                    }
                });

        titleColumn.setCellValueFactory((biddedProposal) -> {
            SimpleStringProperty property = new SimpleStringProperty();
            if (biddedProposal == null)
                property.setValue(null);
            else
                property.setValue(biddedProposal.getValue().getProposal().getTitle());
            return property;
        });

        resultColumn.setCellValueFactory((biddedProposal) -> {
            SimpleStringProperty property = new SimpleStringProperty();
            if (biddedProposal == null)
                property.setValue(null);
            else
                property.setValue(biddedProposal.getValue().getResult());
            return property;
        });
    }

    private void initReviewer() {
        int idEdition = (int)getData().get("idEdition");
        List<Reviewer> reviewers = reviewerService.getAllReviewersForEdition(new FindReviewersByEditionRequest(idEdition));
        listReviewers.setItems(FXCollections.observableArrayList(reviewers));

        listReviewers.setCellFactory(param -> new ListCell<Reviewer>() {
            @Override
            protected void updateItem(Reviewer item, boolean empty) {
                super.updateItem(item, empty);

                if ((item == null) || (item.getUser() == null)) {
                    setText(null);
                } else {
                    setText(item.getUser().getUsername() + "(" + item.getUser().getName() + ")");
                }
            }
        });

        listReviewers.getSelectionModel()
                     .selectedItemProperty()
                     .addListener((observable, oldValue, newValue) ->{
                                 selectedReviewer = ((Reviewer)newValue);
                                 reviewerTxt.setText(selectedReviewer.getUser().getUsername());
                             });
    }

    public void assign(){
        if ((selectedReviewer == null) || (selectedPaper == null)){
            showDialog("Please select a reviewer and a paper!","Ooops!");
            return;
        }

        boolean added = reviewerService.assignPaperToReviewer(new AssignPaperToReviewerRequest(selectedReviewer.getId(), selectedPaper.getProposal().getId(), Result.BORDERLINE_ACCEPT.getName()));
        if (added)
            showDialog("Paper assigned to reviewer!", "Success!");
        else
            showDialog("Sorry, this paper might be already assigned to this reviewer.", "Error!");
    }
}
