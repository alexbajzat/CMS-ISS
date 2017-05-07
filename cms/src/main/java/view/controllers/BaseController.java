package view.controllers;

import javafx.scene.control.Alert;

import java.util.HashMap;

/**
 * Created by Paul on 5/7/17.
 */
public abstract class BaseController {
    // the data is used to send objects between screens
    private HashMap<String, Object> data;

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    protected void showDialog(String text, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.show();
    }

    /**
     * Called when the app can be started, and the data is loaded
     */
    public void start() {
    }
}
