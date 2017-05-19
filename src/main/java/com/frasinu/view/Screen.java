package com.frasinu.view;

/**
 * Created by Paul on 5/7/17.
 */
public enum Screen {
    ABOUT("about.fxml", "About this app","com.frasinu.view.controllers.AboutController"),
    ATTEND("attend.fxml","Attend to a conference","com.frasinu.view.controllers.AttendController"),
    CONFERENCE("conference.fxml", "Conference","com.frasinu.view.controllers.ConferenceController"),
    CONFERENCES("conferences.fxml", "Conferences","com.frasinu.view.controllers.ConferencesController"),
    LOGIN("login.fxml", "Login","com.frasinu.view.controllers.LoginController"),
    PAPER("papers.fxml","Papers","com.frasinu.view.controllers.PaperController"),
    REGISTER("register.fxml", "Register","com.frasinu.view.controllers.RegisterController"),
    TEST("test.fxml", "Test","com.frasinu.view.controllers.TestController");

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
