package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

public class TourDetails {
    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private String tour_photo;
    @SerializedName("name")
    private String tour_name;
    @SerializedName("cost")
    private String tour_cost;
    @SerializedName("date")
    private String tour_date;
    @SerializedName("capacity")
    private String tour_number;
    @SerializedName("details")
    private String tour_details;
    @SerializedName("description")
    private String tour_description;
    @SerializedName("category")
    private String tour_category;
    @SerializedName("location")
    private String tour_location;
    @SerializedName("reserved_number")
    private String tour_reserved_number;
    @SerializedName("remaining_capacity")
    private String remaining_capacity ;

    public String getRemaining_capacity() {
        return remaining_capacity;
    }

    @SerializedName("has_user_reserved_before")
    private boolean hasUserReservedBefore;

    public TourDetails(String tour_name, String tour_cost, String tour_date, String tour_photo, String tour_number, String tour_description, String tour_details, String tour_gallery) {
        this.tour_name = tour_name;
        this.tour_cost = tour_cost;
        this.tour_date = tour_date;
        this.tour_photo = tour_photo;
        this.tour_number = tour_number;
        this.tour_description = tour_description;
        this.tour_details = tour_details;
    }

    public TourDetails(String tour_name, String tour_cost, String tour_date, String tour_photo, String tour_number) {
        this.tour_name = tour_name;
        this.tour_cost = tour_cost;
        this.tour_date = tour_date;
        this.tour_photo = tour_photo;
        this.tour_number = tour_number;
    }

    public TourDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTour_details() {
        return tour_details;
    }

    public void setTour_details(String tour_details) {
        this.tour_details = tour_details;
    }

    public String getTour_description() {
        return tour_description;
    }

    public void setTour_description(String tour_description) {
        this.tour_description = tour_description;
    }

    public String getTour_number() {
        return tour_number;
    }

    public void setTour_number(String tour_number) {
        this.tour_number = tour_number;
    }

    public String getTour_name() {
        return tour_name;
    }

    public void setTour_name(String tour_name) {
        this.tour_name = tour_name;
    }

    public String getTour_cost() {
        return tour_cost;
    }

    public void setTour_cost(String tour_cost) {
        this.tour_cost = tour_cost;
    }

    public String getTour_date() {
        return tour_date;
    }

    public void setTour_date(String tour_date) {
        this.tour_date = tour_date;
    }

    public String getTour_photo() {
        return tour_photo;
    }

    public void setTour_photo(String tour_photo) {
        this.tour_photo = tour_photo;
    }

    public String getTour_category() {
        return tour_category;
    }

    public void setTour_category(String tour_category) {
        this.tour_category = tour_category;
    }

    public String getTour_location() {
        return tour_location;
    }

    public void setTour_location(String tour_location) {
        this.tour_location = tour_location;
    }

    public String getTour_reserved_number() {
        return tour_reserved_number;
    }

    public void setTour_reserved_number(String tour_reserved_number) {
        this.tour_reserved_number = tour_reserved_number;
    }

    public boolean isHasUserReservedBefore() {
        return hasUserReservedBefore;
    }
}
