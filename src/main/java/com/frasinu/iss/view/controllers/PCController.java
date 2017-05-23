package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Author;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.model.User;
import com.frasinu.iss.service.AuthorService;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.ReviewerService;
import com.frasinu.iss.service.UserService;
import com.frasinu.iss.service.service_requests.author.CreateAuthorRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.reviewer.FindReviewerByIdRequest;
import com.frasinu.iss.service.service_requests.reviewer.UpdateReviewerRequest;
import com.frasinu.iss.service.service_requests.user.FindByIdRequest;
import com.frasinu.iss.service.service_requests.user.FindIfUserIsAuthorRequest;
import com.frasinu.iss.service.service_requests.user.UpdateUserRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Ericqw on 22.05.2017.
 */
@Controller(value = "PCController")
public class PCController extends BaseController{
    @FXML
    TextField name,email,affiliation,webpage;
    private ConferenceEditionService conferenceEditionService;
    private UserService userService;
    private AuthorService authorService;
    private ReviewerService reviewerService;


    @Autowired
    public void setReviewerService(ReviewerService reviewerService) {
        this.reviewerService=reviewerService;
    }

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

    @Override
    public void setData(HashMap<String, Object> data){
        super.setData(data);
        init();
    }

    public void init(){
        int idReviewer=(int)getData().get("idReviewer");
        int idUser=(int)getData().get("idUser");
        User user= userService.findById(new FindByIdRequest(idUser));
        Reviewer reviewer=reviewerService.findById(new FindReviewerByIdRequest(idReviewer));
        if(user.getName()!=null )
            name.setText(user.getName());
        if(reviewer.getEmail()!=null)
            email.setText(reviewer.getEmail());
        if(reviewer.getAffiliation()!=null)
            affiliation.setText(reviewer.getAffiliation());
        if(reviewer.getWebpage()!=null)
            webpage.setText(reviewer.getWebpage());

    }

    public void update(){
        int idReviewer=(int)getData().get("idReviewer");
        int idUser=(int)getData().get("idUser");
        User user= userService.findById(new FindByIdRequest(idUser));
        Reviewer reviewer=reviewerService.findById(new FindReviewerByIdRequest(idReviewer));
        User newUser=User.builder().setId(user.getId())
                .setName(name.getText())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword()).build();
        Reviewer newReviewer=Reviewer.builder().setId(reviewer.getId())
                .setEmail(email.getText())
                .setAffiliation(affiliation.getText())
                .setWebpage(webpage.getText())
                .setUser(reviewer.getUser())
                .setConferenceEdition(reviewer.getConferenceEdition()).build();
        try {
            userService.updateUserPasswordEncoded(new UpdateUserRequest(newUser.getId(),newUser.getName(),newUser.getUsername(),newUser.getPassword()));
        } catch (ValidationException e) {
            showDialog(e.getMessage(), "Ooops!");
        }
        reviewerService.updateReviewer(new UpdateReviewerRequest(newReviewer.getId(),newReviewer.getAffiliation(),newReviewer.getEmail(),newReviewer.getWebpage(),newReviewer.getUser(),newReviewer.getConferenceEdition()));
    }

    public void seeSchedule(javafx.event.ActionEvent ac){
        FrasinuApplication.changeScreen(Screen.SCHEDULE,getData());
    }
    public void seeConferenceInfo(javafx.event.ActionEvent ac){FrasinuApplication.changeScreen(Screen.CONFERENCEINFO,getData());}
    public void goToAuthor(javafx.event.ActionEvent ac){

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
    public void goToSteeringCom(javafx.event.ActionEvent ac){
        FrasinuApplication.changeScreen(Screen.STEERING, getData());
    }

}
