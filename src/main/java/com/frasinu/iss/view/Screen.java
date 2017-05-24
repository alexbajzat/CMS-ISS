package com.frasinu.iss.view;

/**
 * Created by Paul on 5/7/17.
 */
public enum Screen {
    ABOUT("about.fxml", "About this app","AboutController"),
    ATTEND("attend.fxml","Attend to a conference","AttendController"),
    CONFERENCEINFO("conferenceInfo.fxml","Conference info","ConferenceInfoController"),
    CONFERENCES("conferences.fxml", "Conferences","ConferencesController"),
    LOGIN("login.fxml", "Login","LoginController"),
    MAKEREVIEW("makeReview.fxml","Make a review","MakeReviewController"),
    AUTHOR("menuAuthor.fxml","Menu Author","AuthorController"),
    PAPER("paper.fxml","Paper","PaperController"),
    REGISTER("register.fxml", "Register","RegisterController"),
    SCHEDULE("schedule.fxml", "Schedule","ScheduleController"),
    BIDS("bids.fxml","See Bids","BidsController"),
    PROPOSALS("proposals.fxml","See Proposals","ProposalsController"),
    PCMEMBER("menuPC.fxml","Menu PCMember","PCController"),
    STEERING("menuSteering.fxml","Steering Member","SteeringComController"),
    REVIEWS("reviews.fxml","See Reviews","ReviewsController"),
    TEST("test.fxml", "Test","TestController"),
    MENUADMIN("menuAdmin.fxml","Menu for admin","AdminMenuController"),
    ADMINCONFERENCES("adminConferences.fxml","Update Conferences","AdminConferencesController"),
    ADMINUSERS("adminUsers.fxml","","AdminUsersController"),
    ADMINEDITION("adminEdition.fxml","Menu for admin","AdminEditionsController");


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
