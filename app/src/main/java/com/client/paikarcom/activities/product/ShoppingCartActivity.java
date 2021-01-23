package com.client.paikarcom.activities.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;
import com.client.paikarcom.adapters.ShoppingCartRecyclerAdapter;
import com.client.paikarcom.databinding.ActivityShoppingCartBinding;
import com.client.paikarcom.extras.OrderPlacementManager;
import com.client.paikarcom.extras.Tools;
import com.client.paikarcom.models.Category;
import com.client.paikarcom.models.Product;
import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ActivityShoppingCartBinding activityShoppingCartBinding;
    private ShoppingCartRecyclerAdapter shoppingCartRecyclerAdapter;
    private String from;
    private Category category;
    private Product product;
    private OrderPlacementManager orderPlacementManager;
    private Tools tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityShoppingCartBinding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_cart);

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
        orderPlacementManager = new OrderPlacementManager(this);
        tools = new Tools(this);
    }
    //endregion

    //region perform UI interactions
    private void bindUiWithComponents() {
        //region set sub category recycler adapter
        setSubCategoryRecycler();
        //endregion

        //region place order button
        orderPlacementManager.setOnSubmitOrderClick(new OrderPlacementManager.onSubmitOrderClick() {
            @Override
            public void onSubmitClick() {
                tools.getOrderConfirmationDialog();
                tools.setOnBackClick(new Tools.onBackClick() {
                    @Override
                    public void onBackClick() {
                        tools.closeOrderConfirmationDialog();
                        onBackPressed();
                    }
                });
            }
        });
        orderPlacementManager.onOrderPlacement(activityShoppingCartBinding.placeOrderButton,4,2300,55);
        //endregion
    }
    //endregion

    //region set category recycler adapter
    private void setSubCategoryRecycler(){
        shoppingCartRecyclerAdapter = new ShoppingCartRecyclerAdapter(getProducts());
        activityShoppingCartBinding.shoppingCartRecycler.setLayoutManager(new LinearLayoutManager(this));
        activityShoppingCartBinding.shoppingCartRecycler.setAdapter(shoppingCartRecyclerAdapter);
        shoppingCartRecyclerAdapter.notifyDataSetChanged();

        shoppingCartRecyclerAdapter.setOnRemoveClick(new ShoppingCartRecyclerAdapter.onRemoveClick() {
            @Override
            public void itemClick(ArrayList<Product> products) {
                shoppingCartRecyclerAdapter.notifyDataSetChanged();
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
        if (from.equals("productDetails")) {
            startActivity(new Intent(ShoppingCartActivity.this, ProductDetailsActivity.class).putExtra("category",category).putExtra("product",product));
        }
        else if(from.equals("home")){
            startActivity(new Intent(ShoppingCartActivity.this, HomeActivity.class));
        }
        else if (from.equals("productList")){
            startActivity(new Intent(ShoppingCartActivity.this, ProductListActivity.class).putExtra("category", category));
        }
        else if (from.equals("subCategory")){
            startActivity(new Intent(ShoppingCartActivity.this, SubCategoryActivity.class).putExtra("category", category));
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