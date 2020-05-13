package ir.sahand.tour.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ir.sahand.tour.R;
import ir.sahand.tour.model.TourDetails;
import ir.sahand.tour.TourPage;

public class MainPageRecyclerAdapter extends RecyclerView.Adapter<MainPageRecyclerAdapter.MyViewHolder> {
    Context mContext;
    List<TourDetails> tourDetailsList;

    public MainPageRecyclerAdapter(Context mContext, List<TourDetails> tours) {
        this.mContext = mContext;
        this.tourDetailsList = tours;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.recycleview_card ,parent ,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.tour_name.setText(tourDetailsList.get(position).getTour_name());
        holder.tour_cost.setText(tourDetailsList.get(position).getTour_cost() +" تومان");
        holder.tour_date.setText(tourDetailsList.get(position).getTour_date());
        holder.tour_number.setText(tourDetailsList.get(position).getTour_number()+" نفر");
        String photo_url = tourDetailsList.get(position).getTour_photo();
        Glide
                .with(mContext)
                .load(photo_url)
                .into(holder.tour_img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TourPage.class);
                intent.putExtra("Tour_name" , tourDetailsList.get(position).getTour_name());
                intent.putExtra("Tour_cost" , tourDetailsList.get(position).getTour_cost());
                intent.putExtra("Tour_date" , tourDetailsList.get(position).getTour_date());
                intent.putExtra("Tour_Photo" ,tourDetailsList.get(position).getTour_photo());
                intent.putExtra("Tour_Reserved_Number" , tourDetailsList.get(position).getTour_reserved_number());
                intent.putExtra("Tour_description" ,  tourDetailsList.get(position).getTour_description());
                intent.putExtra("Tour_details" , tourDetailsList.get(position).getTour_details());
                intent.putExtra("Tour_location" , tourDetailsList.get(position).getTour_location());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tourDetailsList == null ? 0 : tourDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView tour_name;
        private TextView tour_cost;
        private TextView tour_date;
        private TextView tour_number;
        private ImageView tour_img;
            public MyViewHolder(View itemView) {
                super(itemView);
                cardView = (CardView) itemView.findViewById(R.id.cardview);
                tour_name = (TextView) itemView.findViewById(R.id.tour_name_cardview);
                tour_cost = (TextView) itemView.findViewById(R.id.cost_tour_cardview);
                tour_date = (TextView) itemView.findViewById(R.id.date_tour_cardview);
                tour_number = (TextView) itemView.findViewById(R.id.number_tour_cardview);
                tour_img = (ImageView) itemView.findViewById(R.id.tour_image_cardview);
            }
    }
}
