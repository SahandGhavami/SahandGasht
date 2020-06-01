package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

public class ReservationResponse {
    @SerializedName("error")
    private boolean error;

    public boolean getError() {
        return error;
    }
}
