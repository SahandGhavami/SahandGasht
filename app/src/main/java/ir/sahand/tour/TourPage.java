package ir.sahand.tour;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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

public class TourPage extends AppCompatActivity {
    private TextView tv_name;
    private ImageView image;
    private TextView tv_date;
    private TextView tv_cost;
    private TextView tv_number;
    private TextView tv_details;
    private TextView tv_location;
    private MainPageRecyclerAdapter adapter;
    private RecyclerView myrecycler;
    private List<TourDetails> tourDetailsList;
    Context mContext;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate  (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_activity);

        toursRequest();

        tv_name = (TextView) findViewById (R.id.tour_activity_name);
        tv_cost = (TextView) findViewById (R.id.tour_activity_cost);
        tv_date = (TextView) findViewById (R.id.tour_activity_date);
        image = (ImageView) findViewById(R.id.tour_activity_image);
        tv_number = (TextView) findViewById(R.id.tour_activity_number);
        tv_details = (TextView) findViewById(R.id.tour_activity_details);
        tv_location= (TextView) findViewById(R.id.tour_activity_location);



        Bundle bundle= getIntent().getExtras();
        String name = bundle.getString("Tour_name");
        String cost = bundle.getString("Tour_cost");
        String date = bundle.getString("Tour_date");
        String reserved_number = bundle.getString("Tour_Reserved_Number");
        String description = bundle.getString("Tour_description");
        String location = bundle.getString("Tour_location");
        String photo = bundle.getString("Tour_Photo");
        Glide
                .with(this)
                .load(photo)
                .into(image);

        tv_name.setText(name);
        tv_cost.setText(cost + " تومان");
        tv_details.setText(description);
        tv_details.setMovementMethod(new ScrollingMovementMethod());
        tv_date.setText(date);
        tv_number.setText("ظرفیت باقی مانده :"+ reserved_number +" نفر");
        tv_location.setText(location);

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
                    Toast.makeText(TourPage.this , "Connection Problem!!" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    protected void recyclerSetting(List<TourDetails> tours) {
        adapter = new MainPageRecyclerAdapter(this, tours);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.tour_activity_recycler);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }

}
