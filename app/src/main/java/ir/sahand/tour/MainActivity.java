package ir.sahand.tour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import ir.sahand.tour.adapter.MainPageRecyclerAdapter;
import ir.sahand.tour.model.TourModel;
import ir.sahand.tour.model.ToursResponse;
import ir.sahand.tour.model.UserResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    View v;
    private MainPageRecyclerAdapter adapter;
    private RecyclerView myrecycler;
    private List<TourModel> tourModelList;
    private TextView offered_tv;
    private TextView special_tv;
    private TextView one_day_tv;
    private TextView username;
    private List<TourModel> tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(AppPreferenceTools.getInstance(getApplicationContext()).isAuthorized()) {
            username = (TextView) findViewById(R.id.mainpage_username);
            registerForContextMenu(username);
            toursRequest("");
            TextView see_more = (TextView) findViewById(R.id.list_more);
            see_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SeeMore.class);
                    offered_tv = (TextView) findViewById(R.id.offered_tour_tv);
                    String text = (String) offered_tv.getText();
                    intent.putExtra("Tour_category", text);
                    startActivity(intent);
                }
            });
            TextView see_more2 = (TextView) findViewById(R.id.list_more_2);
            see_more2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SeeMore.class);
                    special_tv = (TextView) findViewById(R.id.special_tour_tv);
                    String text = (String) special_tv.getText();
                    intent.putExtra("Tour_category", text);
                    startActivity(intent);
                }
            });
            TextView see_more3 = (TextView) findViewById(R.id.list_more_3);
            see_more3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SeeMore.class);
                    one_day_tv = (TextView) findViewById(R.id.one_day_tour_tv);
                    String text = (String) one_day_tv.getText();
                    intent.putExtra("Tour_category", text);
                    startActivity(intent);
                }
            });
        } else {
          startActivity(new Intent(this , FirstPageActivity.class));
          finish();
        }

        /*String token;
        token = AppPreferenceTools.getInstance().getToken();
        Log.d("Token is : " , token);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        userRequest();
    }

    private void toursRequest(String key) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ToursResponse> call = apiInterface.getTour(key);
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
                    Toast.makeText(MainActivity.this, "Connection Problem Tour Request!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void userRequest() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserResponse> call = apiInterface.getUser();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, final Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            username.setText(
                                    "سلام، " +
                                            response.body().getUser().getUser_name()
                                    + "!"
                            );
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.d("user error" , t.getLocalizedMessage());
                    Toast.makeText(MainActivity.this, "error user req", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void recyclerSetting(List<TourModel> list) {
        Collections.sort(list,TourModel.date);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.options_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.my_reserved_tours :
                Intent intent = new Intent(getApplicationContext() , MyTours.class);
                startActivity(intent);
                break;
            case R.id.my_tours :
                Intent intent2 = new Intent(getApplicationContext() , TourOwner.class);
                startActivity(intent2);
                break;
            case R.id.make_tours :
                Intent intent3 = new Intent(getApplicationContext() , MakingTour.class);
                startActivity(intent3);
                break;
            case R.id.logout :
                AppPreferenceTools.getInstance(getApplicationContext()).removeAllPrefs();
                finish();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}