package ir.sahand.tour;

public class TourDetails {
    private static String tour_photo;
    private String tour_name;
    private String tour_cost;
    private String tour_date;
    //private String tour_photo;
    private String tour_number;
    private String tour_details;
    private String tour_description;
    private String tour_gallery;

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

    public String getTour_gallery() {
        return tour_gallery;
    }

    public void setTour_gallery(String tour_gallery) {
        this.tour_gallery = tour_gallery;
    }

    public TourDetails(String tour_name, String tour_cost, String tour_date, String tour_photo , String tour_number , String tour_description , String tour_details , String tour_gallery ) {
        this.tour_name = tour_name;
        this.tour_cost = tour_cost;
        this.tour_date = tour_date;
        this.tour_photo = tour_photo;
        this.tour_number = tour_number;
        this.tour_description=tour_description;
        this.tour_details=tour_details;
        this.tour_gallery=tour_gallery;
    }

    public TourDetails(String tour_name, String tour_cost, String tour_date, String tour_photo , String tour_number ) {
        this.tour_name = tour_name;
        this.tour_cost = tour_cost;
        this.tour_date = tour_date;
        this.tour_photo = tour_photo;
        this.tour_number = tour_number;
    }
    public TourDetails (){}

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

    public static String getTour_photo() {
        return tour_photo;
    }

    public void setTour_photo(String tour_photo) {
        this.tour_photo = tour_photo;
    }
}
