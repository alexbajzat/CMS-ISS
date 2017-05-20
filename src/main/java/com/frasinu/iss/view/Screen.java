package com.frasinu.iss.view;

/**
 * Created by Paul on 5/7/17.
 */
public enum Screen {
    ABOUT("about.fxml", "About this app","AboutController"),
    ATTEND("attend.fxml","Attend to a conference","AttendController"),
    SCHEDULE("schedule.fxml", "Schedule","ScheduleController"),
    CONFERENCES("conferences.fxml", "Conferences","ConferencesController"),
    LOGIN("login.fxml", "Login","LoginController"),
    PAPER("papers.fxml","Papers","PaperController"),
    REGISTER("register.fxml", "Register","RegisterController"),
    TEST("test.fxml", "Test","TestController");

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
