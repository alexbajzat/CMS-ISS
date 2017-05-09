package view.controllers;

import javafx.event.ActionEvent;
import view.FrasinuApplication;
import view.Screen;

/**
 * Created by Paul on 5/9/17.
 */
public class RegisterController extends BaseController {
    public void goToLogin(ActionEvent actionEvent) {
        FrasinuApplication.changeScreen(Screen.LOGIN);
    }
}
