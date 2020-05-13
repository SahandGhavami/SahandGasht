package ir.sahand.tour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ir.sahand.tour.model.TourDetails;

public class JsonParser {
    public static List<TourDetails> parseJason(InputStream input){
        String content = Utils.ConvertInputStreamToString(input);
        return parseJason(content);
    }

    public static List<TourDetails> parseJason(String jsonString) {
        List<TourDetails> oneDayList = new ArrayList<>();

        try {
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0 ; i<json.length() ; i++){
                JSONObject jsonObject = json.getJSONObject(i);
                TourDetails tour = new TourDetails();
                String name = new String(jsonObject.getString("name").getBytes( "ISO-8859-1"),"UTF-8");
                tour.setTour_name(name);
                String cost = new String(jsonObject.getString("Cost").getBytes( "ISO-8859-1"),"UTF-8");
                tour.setTour_cost(cost);
                String date = new String(jsonObject.getString("Date").getBytes( "ISO-8859-1"),"UTF-8");
                tour.setTour_date(date);
                tour.setTour_photo(jsonObject.getString("Photo"));
                String number = new String(jsonObject.getString("number").getBytes( "ISO-8859-1"),"UTF-8");
                tour.setTour_number(number);
                String description = new String(jsonObject.getString("description").getBytes( "ISO-8859-1"),"UTF-8");
                tour.setTour_description(description);
                String details = new String(jsonObject.getString("details").getBytes( "ISO-8859-1"),"UTF-8");
                tour.setTour_description(details);
                oneDayList.add(tour);
            }
        }
        catch (UnsupportedEncodingException | JSONException e){
            e.printStackTrace();
        }
        return oneDayList;
    }



}
