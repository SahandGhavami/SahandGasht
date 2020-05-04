package ir.sahand.tour;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TourGalleryFragment extends Fragment {
    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tour_gallery,container,false);
        return v;
    }
    public static TourGalleryFragment newInstance() {
        Bundle args = new Bundle();

        TourGalleryFragment fragment = new TourGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
