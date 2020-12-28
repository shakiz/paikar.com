package com.client.paikarcom.activities.companyinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.adapters.PaymentSystemAdapter;
import com.client.paikarcom.databinding.ActivityPaymentSystemBinding;
import com.client.paikarcom.models.PaymentSystem;

import java.util.ArrayList;

public class PaymentSystemActivity extends AppCompatActivity {
    private ActivityPaymentSystemBinding activityPaymentSystemBinding;
    private String[] paymentInstructionBkash = {"Dial * 246 #","Select Payment","Give the bKash merchant number: 01xxxxxxxxxx",
            "Give the amount of your order","Use your order number as a reference","Give the counter number 1","Complete the payment with your development PIN"};
    private String[] paymentInstructionRocket = {"Dial * 322 #","Select Payment","Give the rocket merchant number: 01xxxxxxxxxx",
            "Give the amount of your order","Use your order number as a reference","Give the counter number 1","Complete the payment with your Rocket PIN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPaymentSystemBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_system);

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
        setBKashPaymentRecycler();
        setRocketPaymentRecycler();

        activityPaymentSystemBinding.BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    //endregion

    //region set payment recyclers
    private void setBKashPaymentRecycler() {
        PaymentSystemAdapter paymentSystemAdapter = new PaymentSystemAdapter(getBkashInstructions());
        activityPaymentSystemBinding.paymentRecyclerBkash.setLayoutManager(new LinearLayoutManager(this));
        activityPaymentSystemBinding.paymentRecyclerBkash.setAdapter(paymentSystemAdapter);
        paymentSystemAdapter.notifyDataSetChanged();
    }

    private void setRocketPaymentRecycler() {
        PaymentSystemAdapter paymentSystemAdapter = new PaymentSystemAdapter(getRocketInstructions());
        activityPaymentSystemBinding.paymentRecyclerRocket.setLayoutManager(new LinearLayoutManager(this));
        activityPaymentSystemBinding.paymentRecyclerRocket.setAdapter(paymentSystemAdapter);
        paymentSystemAdapter.notifyDataSetChanged();
    }

    private ArrayList<PaymentSystem> getBkashInstructions(){
        ArrayList<PaymentSystem> paymentSystems = new ArrayList<>();
        for (int start = 0; start < paymentInstructionBkash.length; start++) {
            PaymentSystem paymentSystem = new PaymentSystem(paymentInstructionBkash[start]);
            paymentSystems.add(paymentSystem);
        }
        return paymentSystems;
    }

    private ArrayList<PaymentSystem> getRocketInstructions(){
        ArrayList<PaymentSystem> paymentSystems = new ArrayList<>();
        for (int start = 0; start < paymentInstructionRocket.length; start++) {
            PaymentSystem paymentSystem = new PaymentSystem(paymentInstructionRocket[start]);
            paymentSystems.add(paymentSystem);
        }
        return paymentSystems;
    }
    //endregion

    //region activity components
    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaymentSystemActivity.this, HomeActivity.class));
    }
    //endregion
}