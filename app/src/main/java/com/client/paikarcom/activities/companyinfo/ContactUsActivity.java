package com.client.paikarcom.activities.companyinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.databinding.ActivityContactUsBinding;

public class ContactUsActivity extends AppCompatActivity {

    private ActivityContactUsBinding activityContactUsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityContactUsBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);
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
        //region back arrow
        activityContactUsBinding.BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //endregion
    }
    //endregion

    //region activity components
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ContactUsActivity.this, HomeActivity.class));
    }
    //endregion
}