package ir.sahand.tour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TourPage extends AppCompatActivity {
    private TextView tv_name;
    private ImageView image;
    private TextView tv_date;
    private TextView tv_cost;
    private TextView tv_number;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate  (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_activity);

        tv_name = (TextView) findViewById (R.id.tour_activity_name);
        tv_cost = (TextView) findViewById (R.id.tour_activity_cost);
        tv_date = (TextView) findViewById (R.id.tour_activity_date);
        image = (ImageView) findViewById(R.id.tour_activity_image);
        tv_number = (TextView) findViewById(R.id.tour_activity_number);


        Bundle bundle= getIntent().getExtras();
        String name = bundle.getString("Tour_name");
        String cost = bundle.getString("Tour_cost");
        String date = bundle.getString("Tour_date");
        String number = bundle.getString("Tour_Number");
        String description = bundle.getString("Tour_description");
        String detalis = bundle.getString("Tour_detalis");
        int photo  = bundle.getInt("Tour_Photo");

        //Toast.makeText(TourPage.this , cost , Toast.LENGTH_LONG).show();
        tv_name.setText(name);
        tv_cost.setText(cost);
        tv_date.setText(date);
        tv_number.setText(number);
        image.setImageResource(photo);
        //--TODO send data to fragments
        Bundle bundle2 = new Bundle();
        bundle2.putString("Tour_description" , description);
        //bundle2.putString("Tour_details" , detalis);
        AboutTourFragment aboutTourFragment = new AboutTourFragment();
        aboutTourFragment.setArguments(bundle2);
    }

}
