package com.client.paikarcom.activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

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

import com.client.paikarcom.R;
import com.client.paikarcom.activities.companyinfo.AboutUsActivity;
import com.client.paikarcom.activities.companyinfo.ComplaintAndSuggestionActivity;
import com.client.paikarcom.activities.companyinfo.ContactUsActivity;
import com.client.paikarcom.activities.companyinfo.CreditPolicyActivity;
import com.client.paikarcom.activities.companyinfo.PaymentSystemActivity;
import com.client.paikarcom.activities.companyinfo.ReturnPolicyActivity;
import com.client.paikarcom.activities.companyinfo.VehicleServiceActivity;
import com.client.paikarcom.activities.companyinfo.VisionActivity;
import com.client.paikarcom.activities.notification.NotificationActivity;
import com.client.paikarcom.activities.product.ShoppingCartActivity;
import com.client.paikarcom.activities.product.SubCategoryActivity;
import com.client.paikarcom.activities.profile.ProfileActivity;
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
    private LinearLayout navHeaderLayout;

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
        navHeaderLayout = nav_view.getHeaderView(0).findViewById(R.id.navHeaderLayout);
        tools = new Tools(this);
    }
    //endregion

    //region drawer toggle
    private void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
    //endregion

    //region perform UI interactions
    private void bindUiWithComponents() {
        //region set nav drawer item click listener
        nav_view.setNavigationItemSelectedListener(this);
        //endregion

        //region profile click
        navHeaderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });
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
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                break;
            case R.id.returnPolicy:
                startActivity(new Intent(HomeActivity.this, ReturnPolicyActivity.class));
                break;
            case R.id.paymentSystem:
                startActivity(new Intent(HomeActivity.this, PaymentSystemActivity.class));
                break;
            case R.id.vehicle:
                startActivity(new Intent(HomeActivity.this, VehicleServiceActivity.class));
                break;
            case R.id.creditPolicy:
                startActivity(new Intent(HomeActivity.this, CreditPolicyActivity.class));
                break;
            case R.id.aboutUs:
                startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
                break;
            case R.id.contactUs:
                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
                break;
            case R.id.vision:
                startActivity(new Intent(HomeActivity.this, VisionActivity.class));
                break;
            case R.id.complainOrSuggestion:
                startActivity(new Intent(HomeActivity.this, ComplaintAndSuggestionActivity.class));
                break;
        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //endregion
}