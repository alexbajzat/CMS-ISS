package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.*;
import com.frasinu.iss.service.ProposalService;
import com.frasinu.iss.utils.parser.DocumentParser;
import com.frasinu.iss.utils.parser.PaperHolder;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ericqw on 07.06.2017.
 */
@Controller(value = "DetaliedProposalsController")
public class DetaliedProposalsController extends BaseController {
    @Value("${files.root}")
    private String root;
    @FXML
    TextArea content;
    @FXML
    TextField topic,keyword;
    private ProposalService proposalService;
    @FXML
    ListView<Proposal> listProposals;
    @FXML
    TableView<Proposal> proposalInfos;


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
        refresh();


        listProposals.setCellFactory(param -> new ListCell<Proposal>() {
            @Override
            protected void updateItem(Proposal item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getTitle() == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });
        listProposals.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Proposal>() {
            @Override
            public void changed(ObservableValue<? extends Proposal> observable, Proposal oldValue, Proposal newValue) {
                List<Proposal>proposals=new ArrayList<>();
                proposals.add(newValue);
                ObservableList<Proposal> items = FXCollections.observableList(proposals);
                proposalInfos.setItems(items);

                InputStream inputStream=null;
                try{
                    inputStream=new FileInputStream(root+"\\"+newValue.getAbstractPaper());
                }catch(Exception ex){
                    ex.getStackTrace();
                }
                PaperHolder ph= DocumentParser.parseDocx(inputStream);
                content.setText(ph.getContent());
            }
        });
    }
    public void back(ActionEvent actionEvent){
        FrasinuApplication.changeScreen(Screen.PCMEMBER,getData());
    }
    public void filter(ActionEvent actionEvent){
        String topics=topic.getText();
        String keywords=keyword.getText();
        List<Proposal>proposals=new ArrayList<>();
        if(topics.equals("")&&keywords.equals(""))
            showDialog("You need to write a topic or a keyword!", "Ooops!");
        else {
            if(keywords.equals("")){
                for(Proposal p:proposalService.getAll()){
                    for(Topic t:p.getTopics())
                        if(t.getValue().equals(topics))
                            proposals.add(p);
                }
            }
            else
                if (topics.equals("")){
                    for(Proposal p:proposalService.getAll()){
                        for(Keyword k:p.getKeywords())
                            if(k.getValue().equals(keywords))
                                proposals.add(p);
                    }
                }
                else
                {
                    for(Proposal p:proposalService.getAll()){
                        for(Topic t:p.getTopics())
                            for(Keyword k:p.getKeywords())
                                if(k.getValue().equals(keywords)&&t.getValue().equals(topics))
                                    proposals.add(p);
                    }
                }
            ObservableList<Proposal> items = FXCollections.observableList(proposals);
            listProposals.setItems(items);
        }
    }
    public void refresh(){
        List<Proposal>proposals=proposalService.getAll();
        ObservableList<Proposal> items = FXCollections.observableList(proposals);
        listProposals.setItems(items);
    }
}
