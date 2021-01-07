package ir.sahand.tour.webService;

import java.util.Date;

import ir.sahand.tour.model.ReservationResponse;
import ir.sahand.tour.model.SignupResponse;

import ir.sahand.tour.model.TourResponse;
import ir.sahand.tour.model.ToursResponse;
import ir.sahand.tour.model.UserResponse;
import ir.sahand.tour.model.UsersResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("v1/Api.php?apicall=gettour")
    Call<ToursResponse> getTour(@Query("key") String keyword);

    @GET("v1/Api.php?apicall=gettoursbyname")
    Call<ToursResponse> getToursByName(@Query("name") String keyword);

    @GET("v1/Api.php?apicall=gettourbyid")
    Call<TourResponse> getTourById(@Query("id") int id);

    //avaz she be myreservedtours
    @GET("v1/Api.php?apicall=mytours")
    Call<ToursResponse> getmytour();

    @GET("v1/Api.php?apicall=createdtours")
    Call<ToursResponse> getMyCreatedTours();

    @GET("v1/Api.php?apicall=getpassengers")
    Call<UsersResponse> getMyPassengers(@Query("id") int id);

    @GET("v1/Api.php?apicall=cancellation")
    Call<ReservationResponse> cancell(@Query("id") int id);

    @GET("v1/Api.php?apicall=me")
    Call<UserResponse> getUser();

    @POST("v1/Api.php?apicall=adduser")
    @FormUrlEncoded
    Call<SignupResponse> registerUser(@Field("user_name") String name, @Field("user_password") String password, @Field("user_email") String email, @Field("user_lname") String lname, @Field("user_phone_number") String phone_number);

    @POST("v1/Api.php?apicall=creattour")
    @FormUrlEncoded
    Call<TourResponse> createTour(@Field("name") String name, @Field("location") String location, @Field("capacity") String number, @Field("date") String date, @Field("return_date") String return_date, @Field("cost") String cost, @Field("details") String details, @Field("description") String description);

    @POST("v1/Api.php?apicall=login")
    @FormUrlEncoded
    Call<SignupResponse> loginUser(@Field("user_email") String email, @Field("user_password") String password);

    @POST("v1/Api.php?apicall=reservation")
    @FormUrlEncoded
    Call<ReservationResponse> reserve(@Field("tour_id") int tour_id);


}
