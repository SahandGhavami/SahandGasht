package ir.sahand.tour;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import ir.sahand.tour.adapter.ViewPagerAdapter;
import ir.sahand.tour.model.ReservationResponse;
import ir.sahand.tour.model.TourModel;
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
    private Button submit;

    private int id;

    private TourModel tour;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        submit = (Button) findViewById(R.id.tour_activity_button);
        back_btn = (ImageView) findViewById(R.id.back_button_tourpage);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_name = (TextView) findViewById(R.id.tour_activity_name);
        tv_cost = (TextView) findViewById(R.id.tour_activity_cost);
        tv_date = (TextView) findViewById(R.id.tour_activity_date);
        //image = (ImageView) findViewById(R.id.tour_activity_image);
        tv_number = (TextView) findViewById(R.id.tour_activity_number);
        tv_details = (TextView) findViewById(R.id.tour_activity_details);
        tv_location = (TextView) findViewById(R.id.tour_activity_location);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("Tour_id");
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchDataFromServer();
    }

    void fetchDataFromServer() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<TourResponse> call = apiInterface.getTourById(id);
        call.enqueue(new Callback<TourResponse>() {
            @Override
            public void onResponse(Call<TourResponse> call, final Response<TourResponse> response) {
                if (response.isSuccessful()) {
                    tour = response.body().getTour();
                    refreshViews();
                }
            }

            @Override
            public void onFailure(Call<TourResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "Reservatin has failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void refreshViews() {
        ViewPager viewPager = (ViewPager) findViewById (R.id.tour_activity_viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this , tour.getImages());
        viewPager.setAdapter(viewPagerAdapter);

        tv_name.setText(tour.getTour_name());
        tv_cost.setText(Utils.formatMoney(tour.getTour_cost()));
        tv_details.setText(tour.getTour_description());
        tv_details.setMovementMethod(new ScrollingMovementMethod());
        tv_date.setText(Utils.convertTimestampToHumanReadableString(tour.getTour_date()));
        tv_number.setText("ظرفیت باقی مانده :" + tour.getRemaining_capacity() + " نفر");
        tv_location.setText(tour.getTour_location());

        if (tour.isHasUserReservedBefore()) {
            submit.setText("قبلا رزرو کرده اید!");
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelTour(id);
                    submit.setText("رزرو کنید");
                }
            });
        } else {
            submit.setText("رزرو کنید");
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reserveTour(id);
                }
            });
        }
    }

    private void reserveTour(int id) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ReservationResponse> call = apiInterface.reserve(id);
        call.enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, final Response<ReservationResponse> response) {
                if (response.isSuccessful()) {
                    fetchDataFromServer();
                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "Reservation has failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cancelTour(int id) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ReservationResponse> call = apiInterface.cancell(id);
        call.enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, final Response<ReservationResponse> response) {
                if (response.isSuccessful()) {
                    fetchDataFromServer();
                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "Reservation has failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
