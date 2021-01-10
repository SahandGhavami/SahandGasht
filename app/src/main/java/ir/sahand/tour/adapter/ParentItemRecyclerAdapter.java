package ir.sahand.tour.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ir.sahand.tour.R;
import ir.sahand.tour.model.ParentItem;

public class ParentItemRecyclerAdapter extends RecyclerView.Adapter<ParentItemRecyclerAdapter.ParentViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ParentItem> itemList;

    public ParentItemRecyclerAdapter(List<ParentItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ParentItemRecyclerAdapter.ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_parent_recycler_customize, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentItemRecyclerAdapter.ParentViewHolder holder, int position) {
        ParentItem parentItem = itemList.get(position);
        holder.parentItemTitle.setText(parentItem.getList_title());
        //holder.relativeLayout.setBackgroundColor();
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.childRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setInitialPrefetchItemCount(parentItem.getTourModelList().size());

        ChildItemRecyclerAdapter childItemRecyclerAdapter = new ChildItemRecyclerAdapter(holder.parentItemTitle.getContext(), parentItem.getTourModelList());
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setAdapter(childItemRecyclerAdapter);
        holder.childRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;
        private TextView parentItemTitle;
        private RecyclerView childRecyclerView;

        public ParentViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.parent_relative_layout);
            parentItemTitle = (TextView) itemView.findViewById(R.id.parent_title);
            childRecyclerView = (RecyclerView) itemView.findViewById(R.id.child_recycler_view);
        }
    }
}
