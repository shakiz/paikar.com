package com.client.paikarcom.activities.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.client.paikarcom.R;
import com.client.paikarcom.adapters.ProductRecyclerAdapter;
import com.client.paikarcom.databinding.ActivityProductListBinding;
import com.client.paikarcom.models.Category;
import com.client.paikarcom.models.Product;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    private ActivityProductListBinding activityProductListBinding;
    private ProductRecyclerAdapter productRecyclerAdapter;
    private Toolbar toolbar;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProductListBinding = DataBindingUtil.setContentView(this,R.layout.activity_product_list);

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
        //region set sub category recycler adapter
        setSubCategoryRecycler();
        //endregion
    }
    //endregion

    //region set category recycler adapter
    private void setSubCategoryRecycler(){
        productRecyclerAdapter = new ProductRecyclerAdapter(getProducts());
        activityProductListBinding.productRecycler.setLayoutManager(new GridLayoutManager(this,2));
        activityProductListBinding.productRecycler.setAdapter(productRecyclerAdapter);
        productRecyclerAdapter.notifyDataSetChanged();
        productRecyclerAdapter.setOnItemClick(new ProductRecyclerAdapter.onItemClick() {
            @Override
            public void itemClick(Product product) {
                startActivity(new Intent(ProductListActivity.this,ProductDetailsActivity.class).putExtra("product",product).putExtra("category",category));
            }
        });
    }
    private ArrayList<Product> getProducts(){
        ArrayList<Product> allProductList = new ArrayList<>();
        allProductList.add(new Product(1,"Katarivog","It is a long established fact that a reader.",
                getString(R.string.dummy_description),55,58,new int[]{R.drawable.sample_1,R.drawable.sample_2,R.drawable.sample_3},true,1));
        allProductList.add(new Product(2,"Najir Shail","There are many variations of passages of Lorem Ipsum available",
                getString(R.string.dummy_description),60,0,new int[]{R.drawable.sample_1,R.drawable.sample_2,R.drawable.sample_3},true,1));
        allProductList.add(new Product(3,"Miniket (Thin)","Contrary to popular belief",
                getString(R.string.dummy_description),45,0, new int[]{R.drawable.sample_1,R.drawable.sample_2,R.drawable.sample_3},true,1));
        allProductList.add(new Product(4,"Chini Gura","The standard chunk of Lorem Ipsum",
                getString(R.string.dummy_description), 52,55, new int[]{R.drawable.sample_1,R.drawable.sample_2,R.drawable.sample_3},true,1));
        allProductList.add(new Product(5,"Pani Gura","There are many variations of passages",
                getString(R.string.dummy_description), 65,0, new int[]{R.drawable.sample_1,R.drawable.sample_2,R.drawable.sample_3},false,1));
        allProductList.add(new Product(6,"Mota Chal","It was popularised in the 1960s",
                getString(R.string.dummy_description), 40,46, new int[]{R.drawable.sample_1,R.drawable.sample_2,R.drawable.sample_3},true,1));
        return allProductList;
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
        startActivity(new Intent(ProductListActivity.this, SubCategoryActivity.class).putExtra("category",category));
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