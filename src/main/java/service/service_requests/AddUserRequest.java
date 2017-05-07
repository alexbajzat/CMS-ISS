package service.service_requests;

/**
 * Created by bjz on 5/7/2017.
 */
public class AddUserRequest {
    private final String name;
    private final String username;
    private final String password;

    public AddUserRequest(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
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
