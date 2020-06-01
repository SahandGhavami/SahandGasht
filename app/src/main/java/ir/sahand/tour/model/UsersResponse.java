package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {
    @SerializedName("Users")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
