package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

public class TourResponse {
    @SerializedName("tour")
    private TourModel tour;

    public TourModel getTour() {
        return tour;
    }
}
