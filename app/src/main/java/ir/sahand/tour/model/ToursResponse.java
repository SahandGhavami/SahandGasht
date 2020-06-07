package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToursResponse {
    @SerializedName("tours")
    private List<TourDetails> tours;

    public List<TourDetails> getTours() {
        return tours;
    }

    public void setTours(List<TourDetails> tours) {
        this.tours = tours;
    }
}
