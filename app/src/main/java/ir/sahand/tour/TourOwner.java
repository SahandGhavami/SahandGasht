package ir.sahand.tour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import ir.sahand.tour.adapter.SeeMoreRecyclerAdapter;
import ir.sahand.tour.adapter.TourOwnerRecyclerAdapter;
import ir.sahand.tour.model.TourModel;
import ir.sahand.tour.model.ToursResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourOwner extends AppCompatActivity {
    private TourOwnerRecyclerAdapter adapter;
    private List<TourModel> tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_owner);
        toursRequest();
    }

    private void toursRequest() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ToursResponse> call = apiInterface.getMyCreatedTours();
        call.enqueue(new Callback<ToursResponse>() {
            @Override
            public void onResponse(Call<ToursResponse> call, Response<ToursResponse> response) {
                if (response.isSuccessful()) {
                    tours = response.body().getTours();
                    recyclerSetting(tours);
                }
            }

            @Override
            public void onFailure(Call<ToursResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(TourOwner.this, "Connection Problem Tour Request!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void recyclerSetting(List<TourModel> tours) {
        adapter = new TourOwnerRecyclerAdapter(this, tours);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.activity_tour_owner);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }
}
