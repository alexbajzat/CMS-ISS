package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Result;
import com.frasinu.iss.persistance.model.ReviewedProposal;
import com.frasinu.iss.service.ReviewerService;
import com.frasinu.iss.service.service_requests.reviewer.GetReviewedProposalRequest;
import com.frasinu.iss.service.service_requests.reviewer.MakeReviewRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Toshiba on 5/20/2017.
 */
@Controller(value = "MakeReviewController")
public class MakeReviewController extends BaseController {
    private ReviewerService reviewerService;
    private int idPaper;
    private int idReviewer;

    @FXML
    private ComboBox<Result> cbQualifier;
    @FXML
    private TextArea textRecommandation;

    @Override
    public void setData(HashMap<String, Object> data) {
        super.setData(data);
        init();
    }

    private void init(){
        idReviewer = (int) getData().get("idReviewer");
        idPaper = (int) getData().get("idPaper");
        ObservableList<Result> model = FXCollections.observableList(Stream.of(Result.values())
                .collect(Collectors.toList()));
        cbQualifier.setItems(model);


        ReviewedProposal reviewedProposal = reviewerService.getReviewedProposal(new GetReviewedProposalRequest(idReviewer, idPaper));
        if (reviewedProposal != null){
            cbQualifier.setValue(Result.getByName(reviewedProposal.getReview()));
            textRecommandation.setText(reviewedProposal.getRecommendation());
        }
        else    cbQualifier.getSelectionModel().select(3);
    }

    @Autowired
    public void setReviewerService(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }

    public void review(ActionEvent ac){
        boolean added = reviewerService.makeReview(
                new MakeReviewRequest(idReviewer,idPaper,cbQualifier.getValue(),textRecommandation.getText()));
        if (added)
            showDialog("Thank you for review!", "Success!");
        else
            showDialog("Sorry, seems like you can't review this.", "Error!");
    }

    public void back(ActionEvent ae)
    {
        FrasinuApplication.changeScreen(Screen.PCMEMBER,getData());
    }
}
