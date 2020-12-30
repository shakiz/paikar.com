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
import com.client.paikarcom.models.Notification;
import java.util.ArrayList;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerAdapter.ViewHolder> {
    private ArrayList<Notification> notifications;

    public NotificationRecyclerAdapter(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.OrderConfirmationText.setText(notification.getOrderConfirmationText());
        holder.OrderConfirmationTime.setText(notification.getOrderConfirmationTime());
        holder.InvoiceNumber.setText(notification.getInvoiceNumber());

        if (notification.getSeenStatus() == 0){
            holder.SeenStatus.setVisibility(View.VISIBLE);
        }
        else{
            holder.SeenStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item_card_view;
        TextView OrderConfirmationText, OrderConfirmationTime, InvoiceNumber;
        ImageView SeenStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_card_view = itemView.findViewById(R.id.item_card_view);
            OrderConfirmationText = itemView.findViewById(R.id.OrderConfirmationText);
            OrderConfirmationTime = itemView.findViewById(R.id.OrderConfirmationTime);
            InvoiceNumber = itemView.findViewById(R.id.InvoiceNumber);
            SeenStatus = itemView.findViewById(R.id.SeenStatus);
        }
    }
}
