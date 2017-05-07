package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.FrasinuApplication;
import view.Screen;

import java.util.HashMap;

/**
 * Created by Paul on 5/7/17.
 */
public class AboutController extends BaseController {

    @FXML
    private Button magic;

    public void magicClicked(ActionEvent actionEvent) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("info", "Passing info between screens like a boss");
        FrasinuApplication.changeScreen(Screen.TEST, data);
    }
}
