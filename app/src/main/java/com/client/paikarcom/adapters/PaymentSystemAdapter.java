package com.client.paikarcom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.client.paikarcom.R;
import com.client.paikarcom.models.PaymentSystem;
import java.util.ArrayList;

public class PaymentSystemAdapter extends RecyclerView.Adapter<PaymentSystemAdapter.ViewHolder> {
    private ArrayList<PaymentSystem> paymentSystemInstructions;

    public PaymentSystemAdapter(ArrayList<PaymentSystem> paymentSystemInstructions) {
        this.paymentSystemInstructions = paymentSystemInstructions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_payment_system, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaymentSystem paymentSystem = paymentSystemInstructions.get(position);
        holder.Counter.setText((position++)+".");
        holder.ProcessText.setText(paymentSystem.getProcessText());
    }

    @Override
    public int getItemCount() {
        return paymentSystemInstructions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView item_card_view;
        TextView Counter, ProcessText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_card_view = itemView.findViewById(R.id.item_card_view);
            Counter = itemView.findViewById(R.id.Counter);
            ProcessText = itemView.findViewById(R.id.ProcessText);
        }
    }
}
