package model;

/**
 * Created by bjz on 5/7/2017.
 */
public class User {
    private final Integer id;
    private final String name;
    private final String username;
    private final String password;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    User(Integer id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
