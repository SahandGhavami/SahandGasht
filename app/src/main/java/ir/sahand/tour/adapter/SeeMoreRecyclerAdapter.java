package ir.sahand.tour.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ir.sahand.tour.R;
import ir.sahand.tour.Utils;
import ir.sahand.tour.model.TourDetails;
import ir.sahand.tour.TourPage;

public class SeeMoreRecyclerAdapter extends RecyclerView.Adapter<SeeMoreRecyclerAdapter.MyViewHolder> {
    Context mContext;
    List<TourDetails> tourDetailsList;
    List<TourDetails> tourDetailsListFiltered;


    public SeeMoreRecyclerAdapter(Context mContext, List<TourDetails> tourDetailsList) {
        this.mContext = mContext;
        this.tourDetailsList = tourDetailsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.see_more_list_customize, parent, false);
        SeeMoreRecyclerAdapter.MyViewHolder vHolder = new SeeMoreRecyclerAdapter.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tour_name.setText(tourDetailsList.get(position).getTour_name());
        holder.tour_cost.setText(Utils.formatMoney(tourDetailsList.get(position).getTour_cost())
        );
        holder.tour_date.setText(Utils.convertTimestampToHumanReadableString(tourDetailsList.get(position).getTour_date())
        );
        holder.tour_reserved_number.setText(tourDetailsList.get(position).getTour_number()+" نفر رزرو کرده‌");
        String photo_url = tourDetailsList.get(position).getTour_photo();
        Glide
                .with(mContext)
                .load(photo_url)
                .into(holder.tour_img);
        holder.more_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TourPage.class);

                intent.putExtra("Tour_id", tourDetailsList.get(position).getId());
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
                private TextView tour_reserved_number;
                //private TextView tour_location;
                private ImageView tour_img;
                private Button more_details;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    relativeLayout = (RelativeLayout) itemView.findViewById(R.id.see_more_layout);
                    tour_name = (TextView) itemView.findViewById(R.id.see_more_title);
                    tour_cost = (TextView) itemView.findViewById(R.id.see_more_cost);
                    tour_date = (TextView) itemView.findViewById(R.id.see_more_date);
                    tour_reserved_number = (TextView) itemView.findViewById(R.id.see_more_people);
                    tour_img = (ImageView) itemView.findViewById(R.id.see_more_image);
                    //tour_location=(TextView) itemView.findViewById(R.id.tour_activity_location);
                    more_details = (Button) itemView.findViewById(R.id.see_more_button);
                }
            }
}
