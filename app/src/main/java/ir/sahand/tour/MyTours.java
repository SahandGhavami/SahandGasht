package ir.sahand.tour;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import ir.sahand.tour.adapter.SeeMoreRecyclerAdapter;
import ir.sahand.tour.model.TourDetails;
import ir.sahand.tour.model.TourResponse;
import ir.sahand.tour.model.UserResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTours extends AppCompatActivity {
    private SeeMoreRecyclerAdapter adapter;
    private ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tours);

        backbtn = (ImageView) findViewById (R.id.back_button_mytours);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        myTourRequest();
    }

    private void myTourRequest() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<TourResponse> call = apiInterface.getmytour();
        call.enqueue(new Callback<TourResponse>() {
            @Override
            public void onResponse(Call<TourResponse> call, final Response<TourResponse> response) {
                if (response.isSuccessful()) {
                    List<TourDetails> tours = response.body().getTours();
                    recyclerSetting(tours);
                }
            }

            @Override
            public void onFailure(Call<TourResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.d("user error" , t.getLocalizedMessage());
                    Toast.makeText(MyTours.this, "My Tours Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void recyclerSetting(List<TourDetails> tours) {
        adapter = new SeeMoreRecyclerAdapter(this, tours);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.mytours_recycler);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }

}
