package view.controllers;

import javafx.scene.control.Alert;

import java.util.HashMap;

/**
 * Created by Paul on 5/7/17.
 */
public abstract class BaseController {
    private HashMap<String, Object> data;

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    protected void showDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(text);
        alert.show();
    }

    public void start() {
    }
}
