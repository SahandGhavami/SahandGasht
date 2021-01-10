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
import ir.sahand.tour.Utils;
import ir.sahand.tour.model.TourModel;
import ir.sahand.tour.TourPage;

public class ChildItemRecyclerAdapter extends RecyclerView.Adapter<ChildItemRecyclerAdapter.MyViewHolder> {
    Context mContext;
    List<TourModel> tourModelList;

    public ChildItemRecyclerAdapter(Context mContext, List<TourModel> tours) {
        this.mContext = mContext;
        this.tourModelList = tours;
    }

    public ChildItemRecyclerAdapter(List<TourModel> tourModelList) {
        this.tourModelList = tourModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.recycleview_card, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        TourModel tour = tourModelList.get(position);

        holder.tour_name.setText(tourModelList.get(position).getTour_name());
        holder.tour_cost.setText(
                Utils.formatMoney(tourModelList.get(position).getTour_cost())
        );
        holder.tour_date.setText(
                Utils.convertTimestampToHumanReadableString(tourModelList.get(position).getTour_date())
        );
        holder.tour_return_date.setText(
                Utils.convertTimestampToHumanReadableString(tourModelList.get(position).getTour_return_date())
        );
        holder.tour_number.setText(tourModelList.get(position).getTour_number() + " نفر");
        //String photo_url = tourDetailsList.get(position).getTour_photo();

        if (tour.getImages().length > 0) {
            Glide
                    .with(mContext)
                    .load(tour.getImages()[0])
                    .into(holder.tour_img);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TourPage.class);
                intent.putExtra("Tour_id", tourModelList.get(position).getId());
                intent.putExtra("Tour_name", tourModelList.get(position).getTour_name());
                intent.putExtra("Tour_cost", tourModelList.get(position).getTour_cost());
                intent.putExtra("Tour_date", tourModelList.get(position).getTour_date());
                intent.putExtra("Tour_Photo", tourModelList.get(position).getImages());
                intent.putExtra("Tour_Reserved_Number", tourModelList.get(position).getTour_reserved_number());
                intent.putExtra("Tour_description", tourModelList.get(position).getTour_description());
                intent.putExtra("Tour_details", tourModelList.get(position).getTour_details());
                intent.putExtra("Tour_location", tourModelList.get(position).getTour_location());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tourModelList == null ? 0 : tourModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView tour_name;
        private TextView tour_cost;
        private TextView tour_date;
        private TextView tour_return_date;
        private TextView tour_number;
        private ImageView tour_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            tour_name = (TextView) itemView.findViewById(R.id.tour_name_cardview);
            tour_cost = (TextView) itemView.findViewById(R.id.cost_tour_cardview);
            tour_date = (TextView) itemView.findViewById(R.id.date_tour_cardview);
            tour_return_date = (TextView) itemView.findViewById(R.id.return_date_tour_cardview);
            tour_number = (TextView) itemView.findViewById(R.id.number_tour_cardview);
            tour_img = (ImageView) itemView.findViewById(R.id.tour_image_cardview);
        }
    }
}
