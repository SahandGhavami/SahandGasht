package ir.sahand.tour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import ir.sahand.tour.adapter.SeeMoreRecyclerAdapter;
import ir.sahand.tour.adapter.TourOwnerRecyclerAdapter;
import ir.sahand.tour.model.TourModel;

public class TourOwner extends AppCompatActivity {
    private TourOwnerRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_owner);
    }
    protected void recyclerSetting(List<TourModel> tours) {
        adapter = new TourOwnerRecyclerAdapter(this, tours);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.activity_tour_owner);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }
}
