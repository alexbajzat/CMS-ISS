package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ReviewerService;
import com.frasinu.iss.service.service_requests.author.FindAllByConferenceEditionRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindReviewersByEditionRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * Created by Toshiba on 5/20/2017.
 */
@Controller(value = "BidsController")
public class BidsController extends BaseController {
    @FXML
    ListView listReviewers, listPapers;
    @FXML
    TextField paperTxt,reviewerTxt;

    private ReviewerService reviewerService;
    //private ConferenceEditionService editionService;
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService){this.authorService = authorService;}
//
//    @Autowired
//    public void setConfereneEditionService(ConferenceEditionService editionService) {
//        this.editionService = editionService;
//    }

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

        List<Author> authors = authorService.findAllByConferenceEdition( new FindAllByConferenceEditionRequest(idEdition));
                List<Proposal> papers = new ArrayList<>();
        for (Author author:
                authors) {
            for (Proposal paper:
                 author.getProposals()) {
                if (!papers.contains(paper))
                    papers.add(paper);
            }

        }
        listPapers.setItems(FXCollections.observableArrayList(papers));

        listPapers.setCellFactory(param -> new ListCell<Proposal>() {
            @Override
            protected void updateItem(Proposal item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });

        listPapers.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> paperTxt.setText(((Proposal)newValue).getTitle()));
    }

    private void initReviewer() {
        int idEdition = (int)getData().get("idEdition");
        List<Reviewer> reviewers = reviewerService.getAllReviewersForEdition(new FindReviewersByEditionRequest(idEdition));
        listReviewers.setItems(FXCollections.observableArrayList(reviewers));

        listReviewers.setCellFactory(param -> new ListCell<Reviewer>() {
            @Override
            protected void updateItem(Reviewer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUser() == null) {
                    setText(null);
                } else {
                    setText(item.getUser().getUsername() + "(" + item.getUser().getName() + ")");
                }
            }
        });

        listReviewers.getSelectionModel()
                     .selectedItemProperty()
                     .addListener((observable, oldValue, newValue) -> reviewerTxt.setText(((Reviewer)newValue).getUser().getUsername()));
    }
}
