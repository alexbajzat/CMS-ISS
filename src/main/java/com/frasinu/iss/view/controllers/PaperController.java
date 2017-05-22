package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ProposalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

/**
 * Created by cory_ on 19-May-17.
 */
@Controller(value = "PaperController")
public class PaperController extends BaseController{
    private AuthorService authorService;
    private ProposalService proposalService;

    @FXML
    ListView<Author> listAuthors, listExtraAuthors;

    public void init(){
        List<Author> authors = authorService.getAll();
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
