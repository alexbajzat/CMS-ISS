package com.frasinu.iss.view.controllers;

import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.service_requests.author.FindAllByConferenceEditionRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalRequest;
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

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cory_ on 19-May-17.
 */
@Controller(value = "PaperController")
public class PaperController extends BaseController {
    private AuthorService authorService;
    private ProposalService proposalService;

    @Autowired
    private ConferenceEditionService conferenceEditionService;

    @FXML
    ListView<Author> listAuthors, listExtraAuthors;
    @FXML
    TextField titleTxt, fullPaperTxt, abstractPaperTxt, keywordsTxt, topicsTxt;

    public void init() {
        List<Author> authors = authorService.findAllByConferenceEdition(new FindAllByConferenceEditionRequest((int) getData().get(("idEdition"))));

        //remove current author from list
        authors.removeIf((Author a) -> a.getId().equals(getData().get("idAuthor")));
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
    }

    public void goToAuthor(ActionEvent ac) {
        FrasinuApplication.changeScreen(Screen.AUTHOR, getData());
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

    public void upload(ActionEvent ac) throws InexistentException {
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
        authorsId.add((int) getData().get("idAuthor"));
        for (Author author : listExtraAuthors.getItems()
                ) {
            authorsId.add(author.getId());
        }

        List<String> keywordsList = Arrays.stream(keywords).collect(Collectors.toList());
        List<String> topicsList = Arrays.stream(topics).collect(Collectors.toList());
        Integer editionId = (Integer) getData().get("idEdition");
        ConferenceEdition conferenceEdition = conferenceEditionService
                .findByConferenceEditionId(new FindByConferenceEditionIdRequest(editionId));

        try {
            proposalService.createProposalForAuthors(CreateProposalRequest
                    .builder()
                    .setAuthorsId(authorsId)
                    .setAbstractPaper(abstractPaper)
                    .setFullPaper(fullPaper)
                    .setTitle(title)
                    .setKeywords(keywordsList)
                    .setTopics(topicsList)
                    .setConferenceEdition(conferenceEdition)
                    .build()
            );
            showDialog("Paper uploaded successfully!", "Uploaded");
            resetFields();
        }
        catch (InexistentException e){
            showDialog(e.getMessage(),"Ooops!");
        }

    }

    private void resetFields() {
        for (Author author : listExtraAuthors.getItems()
                ) {
            listAuthors.getItems().add(author);
        }
        listExtraAuthors.getItems().clear();
        titleTxt.setText("");
        fullPaperTxt.setText("");
        abstractPaperTxt.setText("");
        topicsTxt.setText("");
        keywordsTxt.setText("");
    }

    @Override
    public void setData(HashMap<String, Object> data) {
        super.setData(data);
        init();
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setProposalService(ProposalService proposalService) {
        this.proposalService = proposalService;
    }
}
