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

public class ShoppingCartRecyclerAdapter extends RecyclerView.Adapter<ShoppingCartRecyclerAdapter.ViewHolder> {
    private ArrayList<Product> productList;
    public int[] singleTotal;
    private int[] singleQuantity;

    public ShoppingCartRecyclerAdapter(ArrayList<Product> productList) {
        this.productList = productList;
        singleTotal = new int[productList.size()];
        singleQuantity = new int[productList.size()];
    }

    //region click listener
    private onRemoveClick onRemoveClick;
    public interface onRemoveClick {
        void itemClick(ArrayList<Product> products);
    }

    public void setOnRemoveClick(onRemoveClick onRemoveClick) {
        this.onRemoveClick = onRemoveClick;
    }
    //endregion

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_shopping_cart_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.ProductTitle.setText(product.getProductTitle());
        holder.ProductPrice.setText(String.valueOf(product.getProductPrice()));
        holder.AmountInKg.setText(String.valueOf(product.getAmountInKg())+" Kg");
        holder.quantityText.setText(String.valueOf(singleQuantity[position]));
        holder.TotalAmount.setText(String.valueOf(singleQuantity[position] * product.getProductPrice()));
        //remove product from cart
        if (onRemoveClick != null){
            holder.DeleteFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productList.remove(product);
                    onRemoveClick.itemClick(productList);
                }
            });
        }
        //endregion
        holder.addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleQuantity[position]++;
                holder.quantityText.setText(String.valueOf(singleQuantity[position]));
                singleTotal[position]= (int) (singleQuantity[position] * product.getProductPrice());
                holder.TotalAmount.setText("$ "+String.valueOf(singleTotal[position]));
            }
        });
        holder.removeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (singleQuantity[position] > 0){
                    singleQuantity[position]--;
                    singleTotal[position] = (int) (singleQuantity[position] * product.getProductPrice());
                    holder.quantityText.setText(String.valueOf(singleQuantity[position]));
                    holder.TotalAmount.setText("$ "+String.valueOf(singleTotal[position]));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView item_card_view;
        TextView ProductTitle, AmountInKg, ProductPrice, TotalAmount, quantityText;
        ImageView removeQuantity, addQuantity;
        ImageView DeleteFromCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_card_view = itemView.findViewById(R.id.item_card_view);
            ProductTitle = itemView.findViewById(R.id.ProductTitle);
            AmountInKg = itemView.findViewById(R.id.AmountInKg);
            ProductPrice = itemView.findViewById(R.id.ProductPrice);
            TotalAmount = itemView.findViewById(R.id.TotalAmount);
            quantityText = itemView.findViewById(R.id.quantityText);
            removeQuantity = itemView.findViewById(R.id.removeQuantity);
            addQuantity = itemView.findViewById(R.id.addQuantity);
            DeleteFromCart = itemView.findViewById(R.id.DeleteFromCart);
        }
    }
}
