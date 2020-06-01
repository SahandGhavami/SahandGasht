package ir.sahand.tour.webService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import ir.sahand.tour.SharedPreferencesHelper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL = "http://192.168.1.5/TOURS/";
    //public static final String URL_CREATE_TOUR = BASE_URL +"createtour";
    //public static final String URL_READ_TOUR = BASE_URL +"gettour";
    //public static final String URL_UPDATE_TOUR = BASE_URL +"updatetour";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            //Gson gson = new GsonBuilder()
                    //.setLenient()
                   // .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                            new OkHttpClient.Builder()
                                    .addInterceptor(new Interceptor() {
                                        @Override
                                        public Response intercept(Chain chain) throws IOException {
                                            String token = SharedPreferencesHelper.get("token");

                                            Request request;
                                            if (token != null) {
                                                request = chain.request().newBuilder()
                                                        .addHeader("Authorization", "Bearer " + token).build();
                                            } else {
                                                request = chain.request().newBuilder().build();
                                            }
                                            return chain.proceed(request);
                                        }
                                    }).build()
                    )
                    .build();
        }
        return retrofit;
    }

}


