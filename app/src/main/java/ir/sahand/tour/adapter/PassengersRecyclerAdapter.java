package ir.sahand.tour.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import ir.sahand.tour.R;
import ir.sahand.tour.model.User;

public class PassengersRecyclerAdapter extends RecyclerView.Adapter<PassengersRecyclerAdapter.MyViewHolder> {
    static List<User> userModelList;
    Context mContext;

    public PassengersRecyclerAdapter(Context mContext , List<User> userModelList ){
        this.mContext = mContext;
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public PassengersRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.passengers_list_customize, parent, false);
        PassengersRecyclerAdapter.MyViewHolder vHolder = new PassengersRecyclerAdapter.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PassengersRecyclerAdapter.MyViewHolder holder, int position) {
        holder.passengers_name.setText(userModelList.get(position).getUser_name());
        holder.passengers_lname.setText(userModelList.get(position).getUser_lname());
        holder.passengers_phoneNumber.setText(userModelList.get(position).getUser_phone_number());
    }

    @Override
    public int getItemCount() {
        return userModelList == null ? 0 : userModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView passengers_name;
        private TextView passengers_lname;
        private TextView passengers_phoneNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            passengers_name = (TextView) itemView.findViewById (R.id.passengers_list_name);
            passengers_lname = (TextView) itemView.findViewById (R.id.passengers_list_lname);
            passengers_phoneNumber = (TextView) itemView.findViewById (R.id.passengers_list_phone_number);
        }
    }
}
