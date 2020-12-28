package com.client.paikarcom.activities.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import com.client.paikarcom.R;
import com.client.paikarcom.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding activitySignUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

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
        //region set spinner adapter
        setSpinnerAdapter();
        //endregion

        activitySignUpBinding.textSignInHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
            }
        });

        activitySignUpBinding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, OtpVerificationActivity.class).putExtra("mobile",activitySignUpBinding.MobileNo.getText().toString()));
            }
        });
    }
    //endregion

    //region set spinner adapter
    private void setSpinnerAdapter(){
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(
                this,
                R.layout.custom_spinner,
                new String[]{"Your District","Patuakhali","Amtoli","Other"}
        );
        ArrayAdapter<String> subDistrictAdapter = new ArrayAdapter<String>(
                this,
                R.layout.custom_spinner,
                new String[]{"Your Sub-District","Kalapara","Baufol","Other"}
        );
        districtAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        subDistrictAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        activitySignUpBinding.District.setAdapter(districtAdapter);
        activitySignUpBinding.SubDistrict.setAdapter(subDistrictAdapter);
    }
    //endregion

    //region activity components
    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
    }
    //endregion
}