package ir.sahand.tour;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    View v;
    private MainPageRecyclerAdapter adapter;
    private RecyclerView myrecycler;
    private List<TourDetails> tourDetailsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream inputStream = getResources().openRawResource(R.raw.one_day_tour);
        tourDetailsList = JsonParser.parseJason(inputStream);

        Toast.makeText(MainActivity.this , "JsonParser : Returned" + tourDetailsList.size() + "items" , Toast.LENGTH_SHORT).show();
        recyclerSetting();
        //navigationClickListener();

        TextView see_more = (TextView) findViewById(R.id.list_more);
        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OneDayTour.class);
                startActivity(intent);
            }
        });
        TextView see_more2 = (TextView) findViewById(R.id.list_more_2);
        see_more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OneDayTour.class);
                startActivity(intent);
            }
        });
        TextView see_more3 = (TextView) findViewById(R.id.list_more_3);
        see_more3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OneDayTour.class);
                startActivity(intent);
            }
        });

    }
    protected void recyclerSetting() {
        adapter = new MainPageRecyclerAdapter(this, tourDetailsList);
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