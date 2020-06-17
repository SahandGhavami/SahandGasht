package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToursResponse {
    @SerializedName("tours")
    private List<TourModel> tours;

    public List<TourModel> getTours() {
        return tours;
    }

    public void setTours(List<TourModel> tours) {
        this.tours = tours;
    }
}
