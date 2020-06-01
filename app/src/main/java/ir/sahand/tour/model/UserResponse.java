package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }
}
