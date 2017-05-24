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
    PAPERUPDATE("paperUpdate.fxml","Update paper","PaperUpdateController"),
    PAPERREVIEWS("paperReviews.fxml","Reviews","PaperReviewController"),
    PAPERPRESENTATION("presentationSchedule.fxml","Presentation","PresentaionScheduleController"),
    REGISTER("register.fxml", "Register","RegisterController"),
    SCHEDULE("schedule.fxml", "Schedule","ScheduleController"),
    BIDS("bids.fxml","See Bids","BidsController"),
    BIDPAPERS("bidpaper.fxml","See Proposals","BidPaperController"),
    PCMEMBER("menuPC.fxml","Menu PCMember","PCController"),
    STEERING("menuSteering.fxml","Steering Member","SteeringComController"),
    REVIEWS("reviews.fxml","See Reviews","ReviewsController"),
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
