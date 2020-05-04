package ir.sahand.tour;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;


public class TourDetailsFragment extends Fragment {
    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tour_details,container,false);
        return v;
    }
    public static android.support.v4.app.Fragment newInstance() {
        Bundle args = new Bundle();

        TourDetailsFragment fragment = new TourDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
