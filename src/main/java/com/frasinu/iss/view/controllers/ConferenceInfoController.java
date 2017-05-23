package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.service.*;
import com.frasinu.iss.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindConferenceByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindByUserAndEditionIdRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import com.frasinu.iss.service.service_requests.user.FindIfUserIsAuthorRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Ericqw on 20.05.2017.
 */
@Controller(value = "ConferenceInfoController")
public class ConferenceInfoController extends BaseController{
    private ConferenceEditionService conferenceEditionService;
    private UserService userService;
    private AuthorService authorService;
    private ReviewerService reviewerService;
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
    public void setReviewerService(ReviewerService reviewerService) {
        this.reviewerService=reviewerService;
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
        name.setText(conference.getName()+"; "+conferenceEdition.getName());
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

        Reviewer reviewer=reviewerService.findByUserAndEditionId(new FindByUserAndEditionIdRequest((int)getData().get("idUser"),(int)getData().get("idEdition")));
        if(reviewer==null) {
                showDialog("You are not part of the Program Committee Members", "Ooops!");
                return;
            }
        else {
            if (reviewer.getEmail()==null && reviewer.getWebpage()==null && reviewer.getAffiliation()==null)
                showDialog("We are glad that you accepted to be a Program Committee Member this year. Please complete your personal info on the left side to " +
                        "complete the registration.", "Info!");

            HashMap<String, Object> map = getData();
            map.put("idReviewer", reviewer.getId());
            FrasinuApplication.changeScreen(Screen.PCMEMBER, getData());
        }
    }

    public void goToSteeringCom(ActionEvent ac){
        FrasinuApplication.changeScreen(Screen.STEERING, getData());
    }


    public void goToConferences(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.CONFERENCES,getData());
    }
}
