package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

public class TourResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("tour")
    private TourModel tour;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public TourModel getTour() {
        return tour;
    }
}

