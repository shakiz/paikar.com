package com.client.paikarcom.activities.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.WindowManager;

import com.client.paikarcom.R;
import com.client.paikarcom.adapters.CategoryRecyclerAdapter;
import com.client.paikarcom.adapters.SlidingImageAdapter;
import com.client.paikarcom.models.Category;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static TabLayout tabLayout;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.banner_image_1,R.drawable.banner_image_2,R.drawable.banner_image_3,R.drawable.banner_image_4,R.drawable.banner_image_5};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private RecyclerView categoryRecycler;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_green_800));
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //region init UI and perform UI interactions
        initUI();
        bindUiWithComponents();
        //endregion
    }

    //region init UI
    private void initUI() {
        mPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabDots);
        categoryRecycler = findViewById(R.id.categoryRecycler);
    }
    //endregion

    //region perform UI interactions
    private void bindUiWithComponents() {
        //region viewpager for image slider
        for(int i=0;i<IMAGES.length;i++) {
            ImagesArray.add(IMAGES[i]);
        }
        mPager.setAdapter(new SlidingImageAdapter(ImagesArray));
        tabLayout.setupWithViewPager(mPager);
        NUM_PAGES =IMAGES.length;
        //auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        //endregion

        //region set category recycler adapter
        setCategoryRecycler();
        //endregion
    }
    //endregion

    //region set category recycler adapter
    private void setCategoryRecycler(){
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(getCategoryData());
        categoryRecycler.setLayoutManager(new GridLayoutManager(this,4));
        categoryRecycler.setAdapter(categoryRecyclerAdapter);
        categoryRecyclerAdapter.notifyDataSetChanged();
    }
    private ArrayList<Category> getCategoryData(){
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.ic_icon_flour,getString(R.string.rice_pulses_flour)));
        categories.add(new Category(R.drawable.ic_icon_oil,getString(R.string.oil_salt_sugar)));
        categories.add(new Category(R.drawable.ic_icon_spices,getString(R.string.spices)));
        categories.add(new Category(R.drawable.ic_icon_vegitables,getString(R.string.vegetables_and_fruits)));
        categories.add(new Category(R.drawable.ic_clothes,getString(R.string.clothing_and_footwear)));
        categories.add(new Category(R.drawable.ic_icon_cosmetics,getString(R.string.cosmetics)));
        categories.add(new Category(R.drawable.ic_icon_kitchen_tools,getString(R.string.kitchen_materials)));
        categories.add(new Category(R.drawable.ic_icon_hardware_tools,getString(R.string.hardware_and_electronics)));
        return categories;
    }
    //endregion

    //region activity components
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
    //endregion
}