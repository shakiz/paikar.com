package com.client.paikarcom.activities.auth;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.client.paikarcom.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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
    }
    //endregion

    //region activity components
    @Override
    public void onBackPressed() {
    }
    //endregion
}