package ir.sahand.tour;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ir.sahand.tour.adapter.OneDayTourRecyclerAdapter;
import ir.sahand.tour.model.TourDetails;
import ir.sahand.tour.model.TourResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneDayTour extends AppCompatActivity {
    View v;
    private Context mContext;
    private OneDayTourRecyclerAdapter adapter;
    private RecyclerView myrecycler;
    private List<TourDetails> tourDetailsList;
    private TextView tour_category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day_tour);
        //InputStream inputStream = getResources().openRawResource(R.raw.one_day_tour);
        toursRequest();
        tour_category = (TextView) findViewById (R.id.tour_category);
        Bundle bundle= getIntent().getExtras();
        String tour_category_text = bundle.getString("Tour_category");
        tour_category.setText(tour_category_text);


    }
    private void toursRequest(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<TourResponse> call = apiInterface.getTour();
        call.enqueue(new Callback<TourResponse>() {
            @Override
            public void onResponse(Call<TourResponse> call, Response<TourResponse> response) {
                if(response.isSuccessful()){
                    List<TourDetails> tours = response.body().getTours();
                    recyclerSetting(tours);
                }
            }

            @Override
            public void onFailure(Call<TourResponse> call, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(OneDayTour.this , "Connection Problem!!" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    protected void recyclerSetting(List<TourDetails> tours) {
        adapter = new OneDayTourRecyclerAdapter(this, tours);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.see_more_recycler);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }
}
