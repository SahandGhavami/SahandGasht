package ir.sahand.tour;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.List;

public class OneDayTour extends AppCompatActivity {
    View v;
    private Context mContext;
    private OneDayTourRecyclerAdapter adapter;
    private RecyclerView myrecycler;
    private List<TourDetails> tourDetailsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day_tour);
        InputStream inputStream = getResources().openRawResource(R.raw.one_day_tour);
        tourDetailsList = JsonParser.parseJason(inputStream);
        recyclerSetting();

    }
    protected void recyclerSetting() {
        adapter = new OneDayTourRecyclerAdapter(this, tourDetailsList);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.see_more_recycler);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }
}
