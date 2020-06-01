package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_password")
    private String user_password;
    @SerializedName("user_email")
    private String user_email;

    public User(){};
    public User(int id , String name , String password , String email){
        this.user_id=id;
        this.user_name=name;
        this.user_password=password;
        this.user_email=email;
    };

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
