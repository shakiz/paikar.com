package com.client.paikarcom.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.client.paikarcom.R;
import com.client.paikarcom.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding activitySignInBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

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
        activitySignInBinding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(activitySignInBinding.MobileNo.getText().toString())) {
                    if (activitySignInBinding.errorMessageLayout.getVisibility() == View.VISIBLE){
                        activitySignInBinding.errorMessageLayout.setVisibility(View.GONE);
                    }
                    startActivity(new Intent(SignInActivity.this, OtpVerificationActivity.class).putExtra("mobile",activitySignInBinding.MobileNo.getText().toString()));
                } else {
                    if (activitySignInBinding.errorMessageLayout.getVisibility() == View.GONE){
                        activitySignInBinding.errorMessageLayout.setVisibility(View.VISIBLE);
                        activitySignInBinding.message.setText(getString(R.string.please_enter_mobile_number));
                    }
                }
            }
        });

        activitySignInBinding.textCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
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