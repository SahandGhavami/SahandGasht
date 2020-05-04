package ir.sahand.tour;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AboutTourFragment extends Fragment {
    View v;
    public TextView about_tv;

    public AboutTourFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_about_tour,container,false);
        about_tv = (TextView) v.findViewById (R.id.about_tour_tv_id);
        String description = getArguments().getString("Tour_description");
        Toast.makeText(getActivity() , description , Toast.LENGTH_LONG).show();


        about_tv.setText(description);
        return v;
    }
    public static android.support.v4.app.Fragment newInstance() {
        Bundle args = new Bundle();
        AboutTourFragment fragment = new AboutTourFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
