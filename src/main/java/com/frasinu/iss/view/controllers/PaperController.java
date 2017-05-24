package com.frasinu.iss.view.controllers;
import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Proposal;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.service.service_requests.proposal.CreateProposalRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cory_ on 19-May-17.
 */
@Controller(value = "PaperController")
public class PaperController extends BaseController{
    private AuthorService authorService;
    private ProposalService proposalService;

    @FXML
    private Button uploadButton;
    @FXML
    private Button updateButton;

    @FXML
    private Pane pane;

    @FXML
    ListView<Author> listAuthors, listExtraAuthors;
    @FXML
    TextField titleTxt, fullPaperTxt, abstractPaperTxt, keywordsTxt, topicsTxt;

    private Proposal currentProposal;
    public void init(){
        String ok=(String)getData().get("justUpdate");
        if (ok!=null && ok.equals("yes")){
            uploadButton.setVisible(false);
            updateButton.setVisible(true);
            pane.setVisible(false);
            keywordsTxt.setVisible(false);
            topicsTxt.setVisible(false);
            currentProposal=(Proposal)getData().get("proposal");
            titleTxt.setEditable(false);

            titleTxt.setText(currentProposal.getTitle());
            fullPaperTxt.setText(currentProposal.getFullPaper());
            abstractPaperTxt.setText(currentProposal.getAbstractPaper());


        }
        else {
            uploadButton.setVisible(true);
            updateButton.setVisible(false);
            pane.setVisible(true);
            keywordsTxt.setVisible(true);
            topicsTxt.setVisible(true);
            titleTxt.setEditable(true);


        }

        List<Author> authors = authorService.getAll();
        //remove current author from list
        authors.removeIf((Author a)-> a.getId().equals(getData().get("idAuthor")));
        ObservableList<Author> items = FXCollections.observableList(authors);
        listAuthors.setItems(items);

        listAuthors.setCellFactory(param -> new ListCell<Author>() {
            @Override
            protected void updateItem(Author item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUser() == null) {
                    setText(null);
                } else {
                    setText(item.getUser().getUsername());
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
                    setText(item.getUser().getUsername());
                }
            }
        });
    }

    public void goToAuthor(ActionEvent ac){ FrasinuApplication.changeScreen(Screen.AUTHOR, getData());}

    public void addExtraAuthor(ActionEvent ac){
        Author author = listAuthors.getSelectionModel().getSelectedItem();
        if (author == null)
            showDialog("Please select an author from the list on the left.", "Author not selected");
        else {
            listExtraAuthors.getItems().add(author);
            listAuthors.getItems().remove(author);
        }

    }

    public void removeExtraAuthor(ActionEvent ac){
        Author author = listExtraAuthors.getSelectionModel().getSelectedItem();
        if (author == null)
            showDialog("Please select an author from the list on the right.", "Author not selected");
        else {
            listAuthors.getItems().add(author);
            listExtraAuthors.getItems().remove(author);
        }
    }

    public void upload(ActionEvent ac){
        String title = titleTxt.getText();
        if (title.isEmpty()) {
            showDialog("Please enter a title ", "ERROR");
            return;
        }
        String fullPaper = fullPaperTxt.getText();
        String abstractPaper = abstractPaperTxt.getText();
        String[] keywords = keywordsTxt.getText().split(",");
        String[] topics = topicsTxt.getText().split(",");

        List<Integer> authorsId = new ArrayList<>();
        authorsId.add((int)getData().get("idAuthor"));
        for (Author author: listExtraAuthors.getItems()
             ) {
            authorsId.add(author.getId());
        }

        List<String> keywordsList = Arrays.stream(keywords).collect(Collectors.toList());
        List<String> topicsList = Arrays.stream(topics).collect(Collectors.toList());


        proposalService.createProposalForAuthors(CreateProposalRequest
                        .builder()
                        .setAuthorsId(authorsId)
                        .setAbstractPaper(abstractPaper)
                        .setFullPaper(fullPaper)
                        .setTitle(title)
                        .setKeywords(keywordsList)
                        .setTopics(topicsList)
                .build()
                     );

        showDialog("Paper uploaded successfully!", "Uploaded");
        resetFields();
    }

    public void update(ActionEvent ac){

        String fullPaper = fullPaperTxt.getText();
        String abstractPaper = abstractPaperTxt.getText();

        currentProposal.setAbstractPaper(abstractPaper);
        currentProposal.setFullPaper(fullPaper);

        proposalService.updateProposal(currentProposal);

        showDialog("Paper updated successfully!", "Updated");
        resetFields();
    }

    private void resetFields() {
        for (Author author: listExtraAuthors.getItems()
             ) {
            listAuthors.getItems().add(author);
            listExtraAuthors.getItems().remove(author);
        }

        titleTxt.setText("");
        fullPaperTxt.setText("");
        abstractPaperTxt.setText("");
        topicsTxt.setText("");
        keywordsTxt.setText("");

    }

    @Override
    public void setData(HashMap<String, Object> data){
        super.setData(data);
        init();
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setProposalService(ProposalService proposalService) { this.proposalService = proposalService; }
}
