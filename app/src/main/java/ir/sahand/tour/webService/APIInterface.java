package ir.sahand.tour.webService;
import ir.sahand.tour.model.ReservationResponse;
import ir.sahand.tour.model.SignupResponse;

import ir.sahand.tour.model.TourResponse;
import ir.sahand.tour.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("v1/Api.php?apicall=gettour")
    Call<TourResponse> getTour(@Query("key") String keyword);

    @GET("v1/Api.php?apicall=mytours")
    Call<TourResponse> getmytour();

    @GET("v1/Api.php?apicall=me")
    Call<UserResponse> getUser();

    @POST("v1/Api.php?apicall=adduser")
    @FormUrlEncoded
    Call<SignupResponse> registerUser(@Field("user_name") String name , @Field("user_password")String password , @Field("user_email") String email);

    @POST("v1/Api.php?apicall=login")
    @FormUrlEncoded
    Call<SignupResponse> loginUser(@Field("user_email") String email , @Field("user_password")String password);

    @POST("v1/Api.php?apicall=reservation")
    @FormUrlEncoded
    Call<ReservationResponse> reserve (@Field("tour_id")int tour_id);




}