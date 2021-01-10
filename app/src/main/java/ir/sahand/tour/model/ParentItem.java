package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParentItem {

    @SerializedName("name")
    private String list_title;
    @SerializedName("color")
    private String background_color;
    @SerializedName("tours")
    private List<TourModel> tourModelList;

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public ParentItem(String list_title, List<TourModel> tourModelList) {
        this.list_title = list_title;
        this.tourModelList = tourModelList;
    }

    public ParentItem(String list_title, String background_color, List<TourModel> tourModelList) {
        this.list_title = list_title;
        this.background_color = background_color;
        this.tourModelList = tourModelList;
    }

    public String getList_title() {
        return list_title;
    }

    public void setList_title(String list_title) {
        this.list_title = list_title;
    }

    public List<TourModel> getTourModelList() {
        return tourModelList;
    }

    public void setTourModelList(List<TourModel> tourModelList) {
        this.tourModelList = tourModelList;
    }
}
