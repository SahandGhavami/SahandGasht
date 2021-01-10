package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToursResponse {
    @SerializedName("tours")
    private List<TourModel> tours;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<TourModel> getTours() {
        return tours;
    }

    public void setTours(List<TourModel> tours) {
        this.tours = tours;
    }
}
