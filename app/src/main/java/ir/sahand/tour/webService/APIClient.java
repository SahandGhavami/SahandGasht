package ir.sahand.tour.webService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL="http://192.168.1.8/";
    //public static final String URL_CREATE_TOUR = BASE_URL +"createtour";
    //public static final String URL_READ_TOUR = BASE_URL +"gettour";
    //public static final String URL_UPDATE_TOUR = BASE_URL +"updatetour";

    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit == null ){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}


