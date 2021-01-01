package ir.sahand.tour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import ir.sahand.tour.adapter.PassengersRecyclerAdapter;
import ir.sahand.tour.model.TourModel;
import ir.sahand.tour.model.User;
import ir.sahand.tour.model.UsersResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengersActivity extends AppCompatActivity {
    private PassengersRecyclerAdapter adapter;
    private ImageView back_button;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers);
        back_button = (ImageView) findViewById (R.id.back_button_passengers);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("Tour_id");

       myPassengersRequest(id);
    }

    private void myPassengersRequest(int id) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UsersResponse> call = apiInterface.getMyPassengers(id);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, final Response<UsersResponse> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body().getUsers();
                    recyclerSetting(users);
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.d("user error" , t.getLocalizedMessage());
                    Toast.makeText(PassengersActivity.this, "My Passengers Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void recyclerSetting(List<User> users) {
        adapter = new PassengersRecyclerAdapter(this, users);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView myList = (RecyclerView) findViewById (R.id.passengers_recycler);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }
}
