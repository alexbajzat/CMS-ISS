package view.controllers;

import javafx.event.ActionEvent;
import view.FrasinuApplication;
import view.Screen;

/**
 * Created by Paul on 5/9/17.
 */
public class LoginController extends BaseController {
    public void goToRegister(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.REGISTER);
    }
}
