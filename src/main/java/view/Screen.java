package view;

/**
 * Created by Paul on 5/7/17.
 */
public enum Screen {
    ABOUT("about.fxml", "About this app"),
    TEST("test.fxml", "Test"),
    LOGIN("login.fxml", "Login"),
    REGISTER("register.fxml", "Register"),
    CONFERENCE("conferences.fxml", "Conference");

    private final String name;
    private final String title;

    Screen(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }
}
