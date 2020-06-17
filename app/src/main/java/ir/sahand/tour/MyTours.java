package ir.sahand.tour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import ir.sahand.tour.adapter.MyToursRecyclerAdapter;
import ir.sahand.tour.model.TourModel;
import ir.sahand.tour.model.ToursResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTours extends AppCompatActivity {
    private MyToursRecyclerAdapter adapter;
    private ImageView backbtn;
    private TextView tour_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tours);

        backbtn = (ImageView) findViewById (R.id.back_button_mytours);
        tour_size = (TextView) findViewById (R.id.my_tours_size);
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
        Call<ToursResponse> call = apiInterface.getmytour();
        call.enqueue(new Callback<ToursResponse>() {
            @Override
            public void onResponse(Call<ToursResponse> call, final Response<ToursResponse> response) {
                if (response.isSuccessful()) {
                    List<TourModel> tours = response.body().getTours();
                    int size = tours.size();
                    tour_size.setText(  " شما " + size + " رزرو کرده اید.");
                    recyclerSetting(tours);
                }
            }

            @Override
            public void onFailure(Call<ToursResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.d("user error" , t.getLocalizedMessage());
                    Toast.makeText(MyTours.this, "My Tours Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void recyclerSetting(List<TourModel> tours) {
        adapter = new MyToursRecyclerAdapter(this, tours);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.mytours_recycler);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }

}
