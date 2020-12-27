package com.client.paikarcom.activities.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.product.ProductDetailsActivity;
import com.client.paikarcom.activities.product.ShoppingCartActivity;
import com.client.paikarcom.activities.product.SubCategoryActivity;
import com.client.paikarcom.adapters.CategoryRecyclerAdapter;
import com.client.paikarcom.adapters.SlidingImageAdapter;
import com.client.paikarcom.extras.Tools;
import com.client.paikarcom.models.Category;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static ViewPager mPager;
    private static TabLayout tabLayout;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.banner_image_1,R.drawable.banner_image_2,R.drawable.banner_image_3,R.drawable.banner_image_4,R.drawable.banner_image_5};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private RecyclerView categoryRecycler;
    private NavigationView nav_view;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private Tools tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);

        //region setup toolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_green_800));
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //endregion

        //region set drawer
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_icon_menu);
        setupDrawerToggle();
        //endregion

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
        nav_view = findViewById(R.id.nav_view);
        tools = new Tools(this);
    }
    //endregion

    //region drawer toggle
    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
    //endregion

    //region perform UI interactions
    private void bindUiWithComponents() {
        //region set nav drawer item click listener
        nav_view.setNavigationItemSelectedListener(this);
        //endregion

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
        categoryRecyclerAdapter.setOnItemClick(new CategoryRecyclerAdapter.onItemClick() {
            @Override
            public void itemClick(Category category) {
                startActivity(new Intent(HomeActivity.this, SubCategoryActivity.class).putExtra("category", category));
            }
        });
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bag:
                startActivity(new Intent(HomeActivity.this, ShoppingCartActivity.class).putExtra("from","home"));
                return true;
            case R.id.call:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }



    @Override
    public void onBackPressed() {
        tools.exitApp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                break;
            case R.id.returnPolicy:
                break;
            case R.id.paymentSystem:
                break;
            case R.id.vehicle:
                break;
            case R.id.creditPolicy:
                break;
            case R.id.aboutUs:
                break;
            case R.id.contactUs:
                break;
            case R.id.vision:
                break;
            case R.id.complainOrSuggestion:
                break;
        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //endregion
}