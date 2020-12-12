package com.client.paikarcom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.client.paikarcom.R;
import com.client.paikarcom.models.Category;
import java.util.ArrayList;

public class SubCategoryRecyclerAdapter extends RecyclerView.Adapter<SubCategoryRecyclerAdapter.ViewHolder> {
    private ArrayList<Category> subCategoryList;

    public SubCategoryRecyclerAdapter(ArrayList<Category> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    //region click listener
    private onItemClick onItemClick;
    public interface onItemClick{
        void itemClick(Category category);
    }

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    //endregion

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_sub_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = subCategoryList.get(position);
        holder.CategoryText.setText(category.getCategoryText());
        holder.ImageResId.setImageResource(category.getImageResId());
        if (onItemClick != null){
            holder.item_card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.itemClick(category);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item_card_view;
        TextView CategoryText;
        ImageView ImageResId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_card_view = itemView.findViewById(R.id.item_card_view);
            CategoryText = itemView.findViewById(R.id.CategoryText);
            ImageResId = itemView.findViewById(R.id.ImageResId);
        }
    }
}
