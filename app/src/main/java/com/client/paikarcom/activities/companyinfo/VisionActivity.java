package com.client.paikarcom.activities.companyinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.databinding.ActivityVisionBinding;

public class VisionActivity extends AppCompatActivity {
    private ActivityVisionBinding activityVisionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);
        activityVisionBinding = DataBindingUtil.setContentView(this, R.layout.activity_vision);
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
        activityVisionBinding.BackButton.setOnClickListener(new View.OnClickListener() {
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
        startActivity(new Intent(VisionActivity.this, HomeActivity.class));
    }
    //endregion
}