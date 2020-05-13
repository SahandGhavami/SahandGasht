package ir.sahand.tour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ir.sahand.tour.adapter.MainPageRecyclerAdapter;
import ir.sahand.tour.model.TourDetails;
import ir.sahand.tour.model.TourResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    View v;
    private MainPageRecyclerAdapter adapter;
    private RecyclerView myrecycler;
    private List<TourDetails> tourDetailsList;
    private TextView offered_tv ;
    private TextView special_tv;
    private TextView one_day_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //InputStream inputStream = getResources().openRawResource(R.raw.one_day_tour);
        //tourDetailsList = JsonParser.parseJason(inputStream);

        toursRequest();
        //Toast.makeText(MainActivity.this , "JsonParser : Returned" + tourDetailsList.size() + "items" , Toast.LENGTH_SHORT).show();
        TextView see_more = (TextView) findViewById(R.id.list_more);
        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OneDayTour.class);
                offered_tv = (TextView) findViewById(R.id.offered_tour_tv);
                String text= (String) offered_tv.getText();
                intent.putExtra("Tour_category" , text);
                startActivity(intent);
            }
        });
        TextView see_more2 = (TextView) findViewById(R.id.list_more_2);
        see_more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OneDayTour.class);
                special_tv= (TextView) findViewById(R.id.special_tour_tv);
                String text= (String) special_tv.getText();
                intent.putExtra("Tour_category" , text);
                startActivity(intent);
            }
        });
        TextView see_more3 = (TextView) findViewById(R.id.list_more_3);
        see_more3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OneDayTour.class);
                one_day_tv= (TextView) findViewById(R.id.one_day_tour_tv);
                String text= (String) one_day_tv.getText();
                intent.putExtra("Tour_category" , text);
                startActivity(intent);
            }
        });

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
                    Toast.makeText(MainActivity.this , "Connection Problem!!" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void recyclerSetting(List<TourDetails> list) {
        adapter = new MainPageRecyclerAdapter(this, list);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.offered_tour_recycler);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);

        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList2 = (RecyclerView) findViewById(R.id.special_tour_recycler);
        myList2.setLayoutManager(layoutManager2);
        myList2.setAdapter(adapter);

        LinearLayoutManager layoutManager3
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList3 = (RecyclerView) findViewById(R.id.oneday_tour_recycler);
        myList3.setLayoutManager(layoutManager3);
        myList3.setAdapter(adapter);
    }

}