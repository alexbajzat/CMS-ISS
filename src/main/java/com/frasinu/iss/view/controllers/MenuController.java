package com.frasinu.iss.view.controllers;



import com.frasinu.iss.view.FrasinuApplication;
import com.frasinu.iss.view.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Ericqw on 20.05.2017.
 */
public class MenuController extends BaseController {
        public void seeSchedule(ActionEvent ac){
            FrasinuApplication.changeScreen(Screen.SCHEDULE);
        }
        public void seeConferenceInfo(ActionEvent ac){
            //make a new screen
            FrasinuApplication.changeScreen(Screen.CONFERENCEINFO);
        }
        public void publishPaper(ActionEvent ac){
            FrasinuApplication.changeScreen(Screen.PUBLISH);
        }
        public void updatePaper(ActionEvent ac){
            FrasinuApplication.changeScreen(Screen.PUBLISH);
        }

}
