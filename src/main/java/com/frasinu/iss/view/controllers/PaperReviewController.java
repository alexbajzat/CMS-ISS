package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.ReviewedProposal;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.service_requests.proposal.GetAllReviewedByProposalIdRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ericqw on 24.05.2017.
 */

@Controller(value = "PaperReviewController")
public class PaperReviewController extends BaseController {
    private ProposalService proposalService;

    @FXML
    private TableView<ReviewedProposal> tableReviewedProposal;
    @FXML
    private TableColumn<ReviewedProposal, String> reviewerColumn, resultColumn;
    @FXML
    private TextArea textRecommendation;

    @Autowired
    public void setProposalService(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @Override
    public void setData(HashMap<String, Object> data) {
        super.setData(data);
        init();
    }

    private void init() {
        int idPaper = (int) getData().get("idPaper");
        List<ReviewedProposal> reviewedProposal = proposalService.getAllReviewed(new GetAllReviewedByProposalIdRequest(idPaper));

        tableReviewedProposal.setItems(FXCollections.observableArrayList(reviewedProposal));

        reviewerColumn.setCellValueFactory((biddedProposal) -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(biddedProposal.getValue().getReviewer().getUser().getName());
            return property;
        });

        resultColumn.setCellValueFactory((biddedProposal) -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(biddedProposal.getValue().getReview());
            return property;
        });


        tableReviewedProposal.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    if (newValue == null)
                        return;
                    String recommendation = newValue.getRecommendation();
                    if (recommendation != null)
                        textRecommendation.setText(recommendation);
                });
    }

    public void back(ActionEvent actionEvent){
        FrasinuApplication.changeScreen(Screen.CONFERENCEINFO,getData());
    }
}
