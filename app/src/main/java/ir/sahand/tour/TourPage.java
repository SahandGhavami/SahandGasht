package ir.sahand.tour;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ir.sahand.tour.adapter.MainPageRecyclerAdapter;
import ir.sahand.tour.model.ReservationResponse;
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
    private ImageView back_btn;
    private MainPageRecyclerAdapter adapter;
    private RecyclerView myrecycler;
    private List<TourDetails> tourDetailsList;
    Context mContext;
    private Button submit;
    private int id;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate  (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_activity);
        submit = (Button) findViewById (R.id.tour_activity_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserveTour(id);
            }
        });


        Bundle bundle= getIntent().getExtras();
        setData(bundle);
        back_btn=(ImageView) findViewById (R.id.back_button_tourpage);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void reserveTour(int id) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ReservationResponse> call = apiInterface.reserve(id);
        call.enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, final Response<ReservationResponse> response) {
                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(TourPage.this, "Reservatin has failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData(Bundle bundle){
        tv_name = (TextView) findViewById (R.id.tour_activity_name);
        tv_cost = (TextView) findViewById (R.id.tour_activity_cost);
        tv_date = (TextView) findViewById (R.id.tour_activity_date);
        image = (ImageView) findViewById(R.id.tour_activity_image);
        tv_number = (TextView) findViewById(R.id.tour_activity_number);
        tv_details = (TextView) findViewById(R.id.tour_activity_details);
        tv_location= (TextView) findViewById(R.id.tour_activity_location);

        id = bundle.getInt("Tour_id");
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
        tv_cost.setText(Utils.formatMoney(cost));
        tv_details.setText(description);
        tv_details.setMovementMethod(new ScrollingMovementMethod());
        tv_date.setText(Utils.convertTimestampToHumanReadableString(date));
        tv_number.setText("ظرفیت باقی مانده :"+ reserved_number +" نفر");
        tv_location.setText(location);


    }

}
