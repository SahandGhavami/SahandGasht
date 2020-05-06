package ir.sahand.tour;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OneDayTourRecyclerAdapter extends RecyclerView.Adapter<OneDayTourRecyclerAdapter.MyViewHolder> {
    Context mContext;
    List<TourDetails> tourDetailsList;
    List<TourDetails> tourDetailsListFiltered;


    public OneDayTourRecyclerAdapter(Context mContext, List<TourDetails> tourDetailsList) {
        this.mContext = mContext;
        this.tourDetailsList = tourDetailsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.see_more_list_customize, parent, false);
        OneDayTourRecyclerAdapter.MyViewHolder vHolder = new OneDayTourRecyclerAdapter.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tour_name.setText(tourDetailsList.get(position).getTour_name());
        holder.tour_cost.setText(tourDetailsList.get(position).getTour_cost());
        holder.tour_date.setText(tourDetailsList.get(position).getTour_date());
        holder.tour_number.setText(tourDetailsList.get(position).getTour_number());
        String photoName = TourDetails.getTour_photo();
        //--TODO image doesn't working
        if (photoName.contains(".")) {
            photoName = photoName.substring(0, photoName.lastIndexOf("."));
        }
        int imgResId = mContext.getResources().getIdentifier(photoName, " drawable", mContext.getApplicationContext().getPackageName());
        //holder.tour_img.setImageResource(imgResId);
        holder.more_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TourPage.class);
                intent.putExtra("Tour_name", tourDetailsList.get(position).getTour_name());
                intent.putExtra("Tour_cost", tourDetailsList.get(position).getTour_cost());
                intent.putExtra("Tour_date", tourDetailsList.get(position).getTour_date());
                intent.putExtra("Tour_Photo", tourDetailsList.get(position).getTour_photo());
                intent.putExtra("Tour_Number", tourDetailsList.get(position).getTour_number());
                intent.putExtra("Tour_description", tourDetailsList.get(position).getTour_description());
                intent.putExtra("Tour_details", tourDetailsList.get(position).getTour_details());
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return tourDetailsList.size();

    }

            public class MyViewHolder extends RecyclerView.ViewHolder {
                private RelativeLayout relativeLayout;
                private TextView tour_name;
                private TextView tour_cost;
                private TextView tour_date;
                private TextView tour_number;
                private ImageView tour_img;
                private Button more_details;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    relativeLayout = (RelativeLayout) itemView.findViewById(R.id.see_more_layout);
                    tour_name = (TextView) itemView.findViewById(R.id.see_more_title);
                    tour_cost = (TextView) itemView.findViewById(R.id.see_more_cost);
                    tour_date = (TextView) itemView.findViewById(R.id.see_more_date);
                    tour_number = (TextView) itemView.findViewById(R.id.see_more_people);
                    tour_img = (ImageView) itemView.findViewById(R.id.see_more_image);
                    more_details = (Button) itemView.findViewById(R.id.see_more_button);
                }
            }
}
