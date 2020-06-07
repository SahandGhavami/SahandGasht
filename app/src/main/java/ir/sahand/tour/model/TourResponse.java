package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

public class TourResponse {
    @SerializedName("tour")
    private TourDetails tour;

    public TourDetails getTour() {
        return tour;
    }
}
