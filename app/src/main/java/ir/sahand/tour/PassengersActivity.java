package ir.sahand.tour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import ir.sahand.tour.adapter.TourOwnerRecyclerAdapter;
import ir.sahand.tour.model.TourModel;

public class PassengersActivity extends AppCompatActivity {
    private List<TourModel> tours;
    private TourOwnerRecyclerAdapter adapter;
    private ImageView back_button;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passengers);
        back_button = (ImageView) findViewById (R.id.back_button_passengers);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("Tour_id");

    }

    protected void recyclerSetting(List<TourModel> tours) {
        adapter = new TourOwnerRecyclerAdapter(this, tours);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView myList = (RecyclerView) findViewById (R.id.passengers_recycler);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }
}
