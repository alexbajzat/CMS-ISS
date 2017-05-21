package com.frasinu.iss.view.controllers;

import com.frasinu.iss.persistance.model.Conference;
import com.frasinu.iss.persistance.model.ConferenceEdition;
import com.frasinu.iss.service.ConferenceEditionService;
import com.frasinu.iss.service.ConferenceService;
import com.frasinu.iss.service.service_requests.conferenceedition.FindByConferenceEditionIdRequest;
import com.frasinu.iss.service.service_requests.conferenceedition.FindConferenceByConferenceEditionIdRequest;
import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Ericqw on 20.05.2017.
 */
@Controller(value = "ConferenceInfoController")
public class ConferenceInfoController extends BaseController{
    private ConferenceEditionService conferenceEditionService;
    @FXML
    TextArea name,website;
    @FXML
    TextField startDate,endDate,abstractsDeadline,papersDeadline,bidDeadline,evaluationDeadline;

    @Autowired
    public void setConferenceEditionService(ConferenceEditionService conferenceEditionService) {
        this.conferenceEditionService = conferenceEditionService;
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

}
