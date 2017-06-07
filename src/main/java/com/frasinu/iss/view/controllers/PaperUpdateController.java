package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.service_requests.author.FindAllByConferenceEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.proposal.FindByPaperIdRequest;
import com.frasinu.iss.service.service_requests.proposal.UpdateProposalRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ericqw on 24.05.2017.
 */
@Controller(value = "PaperUpdateController")
public class PaperUpdateController extends BaseController {
    @FXML
    ListView<Author> listAuthors, listExtraAuthors;
    @FXML
    TextField titleTxt, fullPaperTxt, abstractPaperTxt, keywordsTxt, topicsTxt;

    @Autowired
    private ProposalService proposalService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ConferenceEditionService conferenceEditionService;
    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setProposalService(ProposalService proposalService) {
        this.proposalService = proposalService;
    }
    @Autowired
    public void setConferenceEditionService(ConferenceEditionService conferenceEditionService) {
        this.conferenceEditionService=conferenceEditionService;
    }

    @Override
    public void setData(HashMap<String, Object> data) {
        super.setData(data);
        init();
    }


    public void init() {
        Proposal proposal = proposalService.findById(new FindByPaperIdRequest((int) getData().get(("idProposal"))));
        List<Author> extraAuthors=proposal.getAuthors();
        ObservableList<Author> extraItems = FXCollections.observableList(extraAuthors);
        listExtraAuthors.setItems(extraItems);

        List<Author> authors = authorService.findAllByConferenceEdition(new FindAllByConferenceEditionRequest((int) getData().get(("idEdition"))));

        //remove current author from list
        authors.removeIf((Author a) ->
        {

            for (Author auth:extraAuthors) {
                if(auth.getId().equals(a.getId()))
                    return true;
            }
            return false;
        });
        ObservableList<Author> items = FXCollections.observableList(authors);
        listAuthors.setItems(items);



        listAuthors.setCellFactory(param -> new ListCell<Author>() {
            @Override
            protected void updateItem(Author item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUser() == null) {
                    setText(null);
                } else {
                    setText(item.getUser().getName());
                }
            }
        });

        listExtraAuthors.setCellFactory(param -> new ListCell<Author>() {
            @Override
            protected void updateItem(Author item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUser() == null) {
                    setText(null);
                } else {
                    setText(item.getUser().getName());
                }
            }
        });
        titleTxt.setText(proposal.getTitle());
        fullPaperTxt.setText(proposal.getFullPaper());
        abstractPaperTxt.setText(proposal.getAbstractPaper());
        keywordsTxt.setText(proposal.getKeywords().stream().map(Object::toString)
                .collect(Collectors.joining(",")));
        topicsTxt.setText(proposal.getTopics().stream().map(Object::toString)
                .collect(Collectors.joining(",")));
    }

    public void addExtraAuthor(ActionEvent ac) {
        Author author = listAuthors.getSelectionModel().getSelectedItem();
        if (author == null)
            showDialog("Please select an author from the list on the left.", "Author not selected");
        else {
            listExtraAuthors.getItems().add(author);
            listAuthors.getItems().remove(author);
        }

    }

    public void removeExtraAuthor(ActionEvent ac) {
        Author author = listExtraAuthors.getSelectionModel().getSelectedItem();
        if (author == null)
            showDialog("Please select an author from the list on the right.", "Author not selected");
        else {
            listAuthors.getItems().add(author);
            listExtraAuthors.getItems().remove(author);
        }
    }

    public void update(ActionEvent ac) {
        if (titleTxt.getText().equals("")) {
            showDialog("Sorry, you have to give a title to your paper!", "Ooops!");
            return;
        }
        String title = titleTxt.getText();
        String fullPaper = fullPaperTxt.getText();
        if (abstractPaperTxt.getText().equals("")) {
            showDialog("Sorry, you have to upload an abstract!", "Ooops!");
            return;
        }
        String abstractPaper = abstractPaperTxt.getText();
        String[] keywords = keywordsTxt.getText().split(",");
        String[] topics = topicsTxt.getText().split(",");

        List<Integer> authorsId = new ArrayList<>();
        for (Author author : listExtraAuthors.getItems()
                ) {
            authorsId.add(author.getId());
        }

        List<String> keywordsList = Arrays.stream(keywords).collect(Collectors.toList());
        List<String> topicsList = Arrays.stream(topics).collect(Collectors.toList());
        Integer editionId = (Integer) getData().get("idEdition");
        ConferenceEdition conferenceEdition = conferenceEditionService
                .findByConferenceEditionId(new FindByConferenceEditionIdRequest(editionId));


        proposalService.updateProposalForAuthors(new UpdateProposalRequest((int) getData().get(("idProposal")),title,abstractPaper,fullPaper,authorsId,keywordsList,topicsList,conferenceEdition));


        showDialog("Paper updated successfully!", "Updated");
    }


    public void back(ActionEvent actionEvent){
        FrasinuApplication.changeScreen(Screen.AUTHOR,getData());
    }
}
