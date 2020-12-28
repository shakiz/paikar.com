package com.client.paikarcom.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding activityProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

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

        //region back arrow
        activityProfileBinding.BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //endregion
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
        activityProfileBinding.District.setAdapter(districtAdapter);
        activityProfileBinding.SubDistrict.setAdapter(subDistrictAdapter);
    }
    //endregion

    //region activity components
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
    }
    //endregion
}