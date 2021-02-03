package ir.sahand.tour;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import ir.sahand.tour.adapter.ChildItemRecyclerAdapter;
import ir.sahand.tour.adapter.ParentItemRecyclerAdapter;
import ir.sahand.tour.model.ParentItem;
import ir.sahand.tour.model.ToursListResponse;
import ir.sahand.tour.model.TourModel;
import ir.sahand.tour.model.UserResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    View v;
    //private ChildItemRecyclerAdapter adapter;
    //private RecyclerView myrecycler;
    private List<TourModel> tourModelList;
    private List<ParentItem> ParentItemList;
    /*private TextView offered_tv;
    private TextView special_tv;
    private TextView one_day_tv;*/
    private TextView username;
    private List<TourModel> tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (AppPreferenceTools.getInstance(getApplicationContext()).isAuthorized()) {
            username = (TextView) findViewById(R.id.mainpage_username);
            //registerForContextMenu(username);
            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomDialog();
                }
            });
            toursRequest();
        } else {
            startActivity(new Intent(this, FirstPageActivity.class));
            finish();
        }

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getApplicationContext(), SeeMore.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        /*String token;
        token = AppPreferenceTools.getInstance().getToken();
        Log.d("Token is : " , token);*/
    }

    private class MenuButtonOnClickListener implements View.OnClickListener {
        private int menuButtonId;
        private AlertDialog alertDialog;

        public MenuButtonOnClickListener(int id, AlertDialog alertDialog) {
            menuButtonId = id;
            this.alertDialog = alertDialog;
        }

        @Override
        public void onClick(View v) {
            switch (menuButtonId) {
                case 0:
                    Intent intent = new Intent(getApplicationContext(), MyTours.class);
                    startActivity(intent);
                    break;
                case 1:
                    Intent intent2 = new Intent(getApplicationContext(), TourOwner.class);
                    startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent(getApplicationContext(), MakingTour.class);
                    startActivity(intent3);
                    break;
                case 3:
                    AppPreferenceTools.getInstance(getApplicationContext()).removeAllPrefs();
                    finish();
                    break;
                default:
                    break;
            }
            alertDialog.dismiss();
        }
    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(R.id.mainpage_relative);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        dialogView.findViewById(R.id.my_reserved_tourss).setOnClickListener(new MenuButtonOnClickListener(0, alertDialog));
        dialogView.findViewById(R.id.my_created_tours).setOnClickListener(new MenuButtonOnClickListener(1, alertDialog));
        dialogView.findViewById(R.id.making_tours).setOnClickListener(new MenuButtonOnClickListener(2, alertDialog));
        dialogView.findViewById(R.id.exit).setOnClickListener(new MenuButtonOnClickListener(3, alertDialog));
    }

    @Override
    protected void onResume() {
        super.onResume();
        userRequest();
        toursRequest();
    }

    private void toursRequest() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ToursListResponse> call = apiInterface.getTourList();
        call.enqueue(new Callback<ToursListResponse>() {
            @Override
            public void onResponse(Call<ToursListResponse> call, Response<ToursListResponse> response) {
                if (response.isSuccessful()) {
                    ParentItemList = response.body().getParentItems();
                    recyclerSetting(ParentItemList);
                }
            }

            @Override
            public void onFailure(Call<ToursListResponse> call, Throwable t) {
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
                    Log.d("user error", t.getLocalizedMessage());
                    Toast.makeText(MainActivity.this, "error user req", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void recyclerSetting(List<ParentItem> list) {
        //Collections.sort(list, ParentItem.date);
        RecyclerView ParentRecyclerView = (RecyclerView) findViewById(R.id.parent_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        ParentItemRecyclerAdapter parentItemRecyclerAdapter = new ParentItemRecyclerAdapter(list);
        ParentRecyclerView.setAdapter(parentItemRecyclerAdapter);
        ParentRecyclerView.setLayoutManager(layoutManager);
        /*adapter = new ChildItemRecyclerAdapter(this, list);
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
        myList3.setAdapter(adapter);*/
    }

   /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(2R.layout.options_menu, menu);
    }*/
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_reserved_tours:
                Intent intent = new Intent(getApplicationContext(), MyTours.class);
                startActivity(intent);
                break;
            case R.id.my_tours:
                Intent intent2 = new Intent(getApplicationContext(), TourOwner.class);
                startActivity(intent2);
                break;
            case R.id.make_tours:
                Intent intent3 = new Intent(getApplicationContext(), MakingTour.class);
                startActivity(intent3);
                break;
            case R.id.logout:
                AppPreferenceTools.getInstance(getApplicationContext()).removeAllPrefs();
                finish();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}