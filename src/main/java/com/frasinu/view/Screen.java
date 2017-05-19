package com.frasinu.view;

import com.frasinu.view.controllers.BaseController;

/**
 * Created by Paul on 5/7/17.
 */
public enum Screen {
    ABOUT("about.fxml", "About this app","com.frasinu.view.controllers.AboutController"),
    TEST("test.fxml", "Test","com.frasinu.view.controllers.TestController"),
    LOGIN("login.fxml", "Login","com.frasinu.view.controllers.LoginController"),
    REGISTER("register.fxml", "Register","com.frasinu.view.controllers.RegisterController"),
    CONFERENCE("conference.fxml", "Conference","com.frasinu.view.controllers.ConferenceController"),
    CONFERENCES("conferences.fxml", "Conferences","com.frasinu.view.controllers.ConferencesController");

    private final String name;
    private final String title;
    private final String controller;

    Screen(String name, String title,String controller) {
        this.name = name;
        this.title = title;
        this.controller=controller;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getController()
    {
        return controller;

    }
}
