package com.client.paikarcom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.client.paikarcom.R;
import com.client.paikarcom.models.Product;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {
    private ArrayList<Product> productList;

    public ProductRecyclerAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    //region click listener
    private onItemClick onItemClick;
    public interface onItemClick{
        void itemClick(Product product);
    }

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    //endregion

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.ProductTitle.setText(product.getProductTitle());
        holder.ProductPrice.setText(String.valueOf(product.getProductPrice()));
        holder.AmountInKg.setText(String.valueOf(product.getAmountInKg()));
        if (onItemClick != null){
            holder.item_card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.itemClick(product);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView item_card_view;
        TextView ProductPrice, ProductTitle, AmountInKg;
        ImageView ImageResId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_card_view = itemView.findViewById(R.id.item_card_view);
            ProductPrice = itemView.findViewById(R.id.ProductPrice);
            ProductTitle = itemView.findViewById(R.id.ProductTitle);
            AmountInKg = itemView.findViewById(R.id.AmountInKg);
        }
    }
}
