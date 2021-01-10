package ir.sahand.tour.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToursListResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("tours")
    private List<ParentItem> parentItems;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<ParentItem> getParentItems() {
        return parentItems;
    }

    public void setParentItems(List<ParentItem> parentItems) {
        this.parentItems = parentItems;
    }
}
