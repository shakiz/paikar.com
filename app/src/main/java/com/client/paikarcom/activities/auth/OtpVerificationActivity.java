package com.client.paikarcom.activities.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.databinding.ActivityOtpVerificationBinding;

public class OtpVerificationActivity extends AppCompatActivity {
    private ActivityOtpVerificationBinding activityOtpVerificationBinding;
    private String phoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOtpVerificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verification);

        //region get intent data
        getIntentData();
        //endregion

        //region init UI and perform UI interactions
        initUI();
        bindUiWithComponents();
        //endregion
    }

    //region load intent data
    private void getIntentData(){
        if (getIntent().getStringExtra("mobile") != null){
            phoneNumber = getIntent().getStringExtra("mobile");
        }
    }
    //endregion

    //region init UI
    private void initUI() {
    }
    //endregion

    //region perform UI interactions
    private void bindUiWithComponents() {
        activityOtpVerificationBinding.MobileNo.setText(phoneNumber);

        activityOtpVerificationBinding.submitOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(activityOtpVerificationBinding.otpCode.getText().toString())) {
                    if (activityOtpVerificationBinding.errorMessageLayout.getVisibility() == View.VISIBLE){
                        activityOtpVerificationBinding.errorMessageLayout.setVisibility(View.GONE);
                    }
                    startActivity(new Intent(OtpVerificationActivity.this, HomeActivity.class));
                } else {
                    if (activityOtpVerificationBinding.errorMessageLayout.getVisibility() == View.GONE){
                        activityOtpVerificationBinding.errorMessageLayout.setVisibility(View.VISIBLE);
                        activityOtpVerificationBinding.message.setText(getString(R.string.please_enter_valid_otp));
                    }
                }
            }
        });
    }
    //endregion

    //region activity components
    @Override
    public void onBackPressed() {
    }
    //endregion
}