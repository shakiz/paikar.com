package com.client.paikarcom.activities.product;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.client.paikarcom.R;
import com.client.paikarcom.databinding.ActivityProductDetailsBinding;
import com.client.paikarcom.models.Category;
import com.client.paikarcom.models.Product;

import de.mateware.snacky.Snacky;

public class ProductDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Product product;
    private Category category;
    private ActivityProductDetailsBinding activityProductDetailsBinding;
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProductDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);

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
        if (getIntent().getExtras().getParcelable("product") != null){
            product = getIntent().getExtras().getParcelable("product");
        }
        if (getIntent().getExtras().getParcelable("category") != null){
            category = getIntent().getExtras().getParcelable("category");
        }
    }
    //endregion

    //region init UI
    private void initUI() {
    }
    //endregion

    //region perform UI interactions
    private void bindUiWithComponents() {
        //region enable all things related to quantity if stock is available
        if (product.isProductStockAvailability()){
            activityProductDetailsBinding.quantityLayout.setEnabled(true);
        }
        else{
            activityProductDetailsBinding.quantityLayout.setEnabled(false);
        }
        //endregion

        //region set inline draw textView and other product data
        if (product != null){
            if (product.getProductOldPrice() > 0){
                activityProductDetailsBinding.ProductOldPrice.setVisibility(View.VISIBLE);
                activityProductDetailsBinding.ProductOldPrice.setPaintFlags(activityProductDetailsBinding.ProductOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (product.isProductStockAvailability()){
                activityProductDetailsBinding.ProductStockAvailability.setText(getString(R.string.available));
            }
            else{
                activityProductDetailsBinding.ProductStockAvailability.setText(getString(R.string.not_available));
            }
            activityProductDetailsBinding.setProduct(product);
        }
        //endregion

        //region change product image
        activityProductDetailsBinding.sampleImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProductImage(R.drawable.sample_1);
            }
        });
        activityProductDetailsBinding.sampleImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProductImage(R.drawable.sample_2);
            }
        });
        activityProductDetailsBinding.sampleImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProductImage(R.drawable.sample_3);
            }
        });
        //endregion

        //region product quantity
        activityProductDetailsBinding.addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.isProductStockAvailability()){
                    quantity++;
                    activityProductDetailsBinding.quantityText.setText(String.valueOf(quantity));
                }
            }
        });
        activityProductDetailsBinding.removeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.isProductStockAvailability()){
                    if (quantity > 0){
                        quantity--;
                        activityProductDetailsBinding.quantityText.setText(String.valueOf(quantity));
                    }
                }
            }
        });
        //endregion

        //region add to cart and buy now listeners
        activityProductDetailsBinding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0){
                    Snacky.builder().setText(getString(R.string.item_added_to_cart)).build();
                }
                else{
                    Toast.makeText(ProductDetailsActivity.this, getString(R.string.quantity_must_be_greater_than_zero), Toast.LENGTH_SHORT).show();
                }
            }
        });

        activityProductDetailsBinding.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0){

                }
                else{
                    Toast.makeText(ProductDetailsActivity.this, getString(R.string.quantity_must_be_greater_than_zero), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //endregion
    }
    //endregion

    //region change product image
    private void changeProductImage(int imageResId){
        activityProductDetailsBinding.ProductThumbnail.setImageResource(imageResId);
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
        startActivity(new Intent(ProductDetailsActivity.this, ProductListActivity.class).putExtra("category",category));
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