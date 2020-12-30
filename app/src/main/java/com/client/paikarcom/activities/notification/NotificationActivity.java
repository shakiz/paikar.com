package com.client.paikarcom.activities.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.adapters.NotificationRecyclerAdapter;
import com.client.paikarcom.databinding.ActivityNotificationBinding;
import com.client.paikarcom.models.Notification;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private ActivityNotificationBinding activityNotificationBinding;
    private NotificationRecyclerAdapter notificationRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);
        activityNotificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);

        //region init UI and perform UI interactions
        initUI();
        bindUiWithComponents();
        //endregion
    }

    //region init UI
    private void initUI() {
    }
    //endregion

    //region perform UI interactions
    private void bindUiWithComponents() {
        //region set notification recycler adapter
        setNotificationRecycler();
        //endregion

        //region back arrow
        activityNotificationBinding.BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //endregion
    }
    //endregion

    //region set notification recycler adapter
    private void setNotificationRecycler(){
        notificationRecyclerAdapter = new NotificationRecyclerAdapter(getNotifications());
        activityNotificationBinding.notificationRecycler.setLayoutManager(new LinearLayoutManager(this));
        activityNotificationBinding.notificationRecycler.setAdapter(notificationRecyclerAdapter);
        notificationRecyclerAdapter.notifyDataSetChanged();
    }

    private ArrayList<Notification> getNotifications(){
        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification(1, getString(R.string.your_order_has_been_confirmed),
                getString(R.string.dummy_time_am), getString(R.string.dummy_invoice_number),0));
        notifications.add(new Notification(2, getString(R.string.your_order_has_been_confirmed),
                getString(R.string.dummy_time_am), getString(R.string.dummy_invoice_number),1));
        notifications.add(new Notification(3, getString(R.string.your_order_has_been_confirmed),
                getString(R.string.dummy_time_am), getString(R.string.dummy_invoice_number),0));
        notifications.add(new Notification(4, getString(R.string.your_order_has_been_confirmed),
                getString(R.string.dummy_time_am), getString(R.string.dummy_invoice_number),1));
        notifications.add(new Notification(5, getString(R.string.your_order_has_been_confirmed),
                getString(R.string.dummy_time_am), getString(R.string.dummy_invoice_number),0));
        notifications.add(new Notification(6, getString(R.string.your_order_has_been_confirmed),
                getString(R.string.dummy_time_am), getString(R.string.dummy_invoice_number),1));
        return notifications;
    }
    //endregion

    //region activity components
    @Override
    public void onBackPressed() {
        startActivity(new Intent(NotificationActivity.this, HomeActivity.class));
    }
    //endregion
}