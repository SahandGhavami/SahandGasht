package ir.sahand.tour.webService;
import ir.sahand.tour.model.TourDetails;

import ir.sahand.tour.model.TourResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("TOURS/v1/Api.php?apicall=gettour")
    Call<TourResponse> getTour(@Query("key") String keyword);
}
