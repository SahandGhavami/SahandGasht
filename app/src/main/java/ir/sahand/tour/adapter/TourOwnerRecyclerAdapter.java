package ir.sahand.tour.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ir.sahand.tour.PassengersActivity;
import ir.sahand.tour.R;
import ir.sahand.tour.Utils;
import ir.sahand.tour.model.TourModel;

public class TourOwnerRecyclerAdapter extends RecyclerView.Adapter<TourOwnerRecyclerAdapter.MyViewHolder> {
    Context mContext;
    static List<TourModel> tourModelList;
     public TourOwnerRecyclerAdapter(Context mContext , List<TourModel> tourModelList ){
         this.mContext = mContext;
         this.tourModelList = tourModelList;
     }

    @NonNull
    @Override
    public TourOwnerRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.tour_owner_list_customize, parent, false);
        TourOwnerRecyclerAdapter.MyViewHolder vHolder = new TourOwnerRecyclerAdapter.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TourOwnerRecyclerAdapter.MyViewHolder holder, final int position) {
         holder.tour_name.setText(tourModelList.get(position).getTour_name());
         holder.tour_date.setText(Utils.convertTimestampToHumanReadableString(tourModelList.get(position).getTour_date()));
         holder.tour_return_date.setText(Utils.convertTimestampToHumanReadableString(tourModelList.get(position).getTour_return_date()));
         holder.tour_edit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(mContext, PassengersActivity.class);
                 intent.putExtra("Tour_id", tourModelList.get(position).getId());
                 mContext.startActivity(intent);
             }
         });
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(mContext, PassengersActivity.class);
                 intent.putExtra("Tour_id", tourModelList.get(position).getId());
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
         private TextView tour_return_date;
         private TextView tour_date;
         private TextView tour_edit;
         public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById (R.id.tour_owner_card_view);
            tour_name = (TextView) itemView.findViewById (R.id.tour_owner_list_name);
            tour_return_date = (TextView) itemView.findViewById (R.id.tour_owner_list_return_date);
            tour_date = (TextView) itemView.findViewById (R.id.tour_owner_list_date);
            tour_edit = (TextView) itemView.findViewById (R.id.tour_owner_list_see);
         }
    }
}
