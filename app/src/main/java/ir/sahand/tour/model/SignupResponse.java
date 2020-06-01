package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignupResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("error")
    private boolean error;

    public String getToken() {
        return token;
    }

    public boolean getError() {
        return error;
    }
}
