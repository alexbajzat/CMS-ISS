package service.service_requests;

/**
 * Created by bjz on 5/7/2017.
 */
public class DeleteUserRequest {
    private final int id;

    public DeleteUserRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
