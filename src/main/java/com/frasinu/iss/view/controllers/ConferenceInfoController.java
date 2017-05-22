package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.ConferenceService;
import com.frasinu.iss.service.UserService;
import com.frasinu.iss.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindConferenceByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import com.frasinu.iss.service.service_requests.user.FindIfUserIsAuthorRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Ericqw on 20.05.2017.
 */
@Controller(value = "ConferenceInfoController")
public class ConferenceInfoController extends BaseController{
    private ConferenceEditionService conferenceEditionService;
    private UserService userService;
    private AuthorService authorService;
    @FXML
    TextArea name,website;
    @FXML
    TextField startDate,endDate,abstractsDeadline,papersDeadline,bidDeadline,evaluationDeadline;

    @Autowired
    public void setConferenceEditionService(ConferenceEditionService conferenceEditionService) {
        this.conferenceEditionService = conferenceEditionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService=userService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService=authorService;
    }


    public void seeSchedule(ActionEvent ac){
        FrasinuApplication.changeScreen(Screen.SCHEDULE, getData());
    }

    @Override
    public void setData(HashMap<String, Object> data){
        super.setData(data);
        init();
    }

    public void init(){
        int idEdition=(int)getData().get("idEdition");
        ConferenceEdition conferenceEdition=conferenceEditionService.findByConferenceEditionId(new FindByConferenceEditionIdRequest(idEdition));
        Conference conference=conferenceEditionService.findConferenceByConferenceEditionId(new FindConferenceByConferenceEditionIdRequest(idEdition));
        if(conference.getName()!=null )
            name.setText(conference.getName());
        if(conference.getName()!=null && conferenceEdition.getName()!=null)
            name.setText(conference.getName()+"; "+conferenceEdition.getName());
        if(conference.getWebpage()!=null)
            website.setText(conference.getWebpage());
        if(conferenceEdition.getConferenceStartDate()!=null)
            startDate.setText(conferenceEdition.getConferenceStartDate().toString());
        if(conferenceEdition.getConferenceEndDate()!=null)
            endDate.setText(conferenceEdition.getConferenceEndDate().toString());
        if(conferenceEdition.getAbstractsDeadline()!=null)
            abstractsDeadline.setText(conferenceEdition.getAbstractsDeadline().toString());
        if(conferenceEdition.getFullPapersDeadline()!=null)
            papersDeadline.setText(conferenceEdition.getFullPapersDeadline().toString());
        if(conferenceEdition.getBiddingDeadline()!=null)
            bidDeadline.setText(conferenceEdition.getBiddingDeadline().toString());
        if(conferenceEdition.getEvaluationDeadline()!=null)
            evaluationDeadline.setText(conferenceEdition.getEvaluationDeadline().toString());


    }

    public void goToAuthor(ActionEvent ac){
        Author author=userService.findIfUserIsAuthor(new FindIfUserIsAuthorRequest((int)getData().get("idUser"),(int)getData().get("idEdition")));
        if(author==null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You are not registered as an author");
            alert.setContentText("Do you want to register?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                showDialog("You are now registered as an author. Please complete your personal info on the left side to " +
                        "complete the registration.", "Info!");
                author = authorService.addAuthor(new CreateAuthorRequest("", "", userService.findById(new FindByIdRequest((int) getData().get("idUser"))),
                        conferenceEditionService.findByConferenceEditionId(new FindByConferenceEditionIdRequest((int) getData().get("idEdition")))));
            } else {
                return;
            }
        }
        HashMap<String, Object> map = getData();
        map.put("idAuthor", author.getId());
        FrasinuApplication.changeScreen(Screen.AUTHOR, getData());
    }
    public void goToPCMember(ActionEvent ac){
        FrasinuApplication.changeScreen(Screen.PCMEMBER, getData());
    }

    public void goToSteeringCom(ActionEvent ac){
        FrasinuApplication.changeScreen(Screen.STEERING, getData());
    }


    public void goToConferences(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.CONFERENCES,getData());
    }
}
