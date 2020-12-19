package com.client.paikarcom.activities.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.adapters.SubCategoryRecyclerAdapter;
import com.client.paikarcom.databinding.ActivitySubCategoryBinding;
import com.client.paikarcom.models.Category;
import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {
    private ActivitySubCategoryBinding activitySubCategoryBinding;
    private Toolbar toolbar;
    private Category category;
    private SubCategoryRecyclerAdapter subCategoryRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySubCategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_sub_category);

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
        if (category != null){
            activitySubCategoryBinding.CategoryText.setText(category.getCategoryText());
        }

        //region set sub category recycler adapter
        setSubCategoryRecycler();
        //endregion
    }
    //endregion

    //region set category recycler adapter
    private void setSubCategoryRecycler(){
        subCategoryRecyclerAdapter = new SubCategoryRecyclerAdapter(getSubCategoryData());
        activitySubCategoryBinding.subCategoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        activitySubCategoryBinding.subCategoryRecycler.setAdapter(subCategoryRecyclerAdapter);
        subCategoryRecyclerAdapter.notifyDataSetChanged();
        subCategoryRecyclerAdapter.setOnItemClick(new SubCategoryRecyclerAdapter.onItemClick() {
            @Override
            public void itemClick(Category category) {
                startActivity(new Intent(SubCategoryActivity.this, ProductListActivity.class).putExtra("category",category));
            }
        });
    }
    private ArrayList<Category> getSubCategoryData(){
        ArrayList<Category> subCategories = new ArrayList<>();
        subCategories.add(new Category(R.drawable.ic_icon_flour,getString(R.string.rice)));
        subCategories.add(new Category(R.drawable.ic_icon_flour,getString(R.string.pulses)));
        subCategories.add(new Category(R.drawable.ic_icon_flour,getString(R.string.flour)));
        subCategories.add(new Category(R.drawable.ic_icon_flour,getString(R.string.suzy)));
        subCategories.add(new Category(R.drawable.ic_icon_flour,getString(R.string.semai)));
        return subCategories;
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
        startActivity(new Intent(SubCategoryActivity.this, HomeActivity.class));
    }

    @Override
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