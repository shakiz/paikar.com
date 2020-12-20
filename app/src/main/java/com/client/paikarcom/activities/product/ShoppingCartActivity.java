package com.client.paikarcom.activities.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.models.Category;
import com.client.paikarcom.models.Product;

public class ShoppingCartActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String from;
    private Category category;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //region setup toolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_green_800));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //endregion

        //region load intent data
        getIntentData();
        //endregion

        //region init UI and perform UI interactions
        initUI();
        bindUiWithComponents();
        //endregion
    }

    //region load intent data
    private void getIntentData(){
        if (getIntent().getStringExtra("from") != null){
            from = getIntent().getStringExtra("from");
        }
        if (getIntent().getExtras().getParcelable("category") != null){
            category = getIntent().getExtras().getParcelable("category");
        }
        if (getIntent().getExtras().getParcelable("product") != null){
            product = getIntent().getExtras().getParcelable("product");
        }
    }
    //endregion

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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (from.equals("productDetails")) {
            startActivity(new Intent(ShoppingCartActivity.this, ProductDetailsActivity.class).putExtra("category",category).putExtra("product",product));
        }
        else if(from.equals("home")){
            startActivity(new Intent(ShoppingCartActivity.this, HomeActivity.class));
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
    //endregion
}