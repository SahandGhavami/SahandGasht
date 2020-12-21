package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_lname")
    private  String user_lname;
    @SerializedName("user_password")
    private String user_password;
    @SerializedName("user_email")
    private String user_email;
    @SerializedName("user_phone_number")
    private String user_phone_number;

    public User(){};
    public User(int id , String name , String password , String email , String lname , String phone_number){
        this.user_id=id;
        this.user_name=name;
        this.user_password=password;
        this.user_email=email;
        this.user_lname=lname;
        this.user_phone_number=phone_number;
    };

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

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
