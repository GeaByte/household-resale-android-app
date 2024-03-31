package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;

import java.util.ArrayList;

public class BuyingActivity extends AppCompatActivity {
    GridView productGV;
    DatabaseHelper databaseHelper;
//    DatabaseHelperSeller databaseHelperSeller;
    private static final String CHANNEL_ID = "reminder";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);
        databaseHelper = new DatabaseHelper(this);
//        databaseHelperSeller = new DatabaseHelperSeller(this);

        //sending reminder notification
//        NotificationHelper.showNotification(this, "Pick-up Reminder", "testing");

        SearchView searchView = findViewById(R.id.searchView);

        productGV = findViewById(R.id.GVbuying);
        ArrayList<ProductModel> productModelArrayList;
        productModelArrayList = databaseHelper.getAllProducts();
        ProductGVAdapter adapter = new ProductGVAdapter(this, productModelArrayList);
        productGV.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ProductModel> filteredList = databaseHelper.searchProducts(newText);
                adapter.clear();
                adapter.addAll(filteredList);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }



    public void onClickSelect(View view){
        int productID = (int) view.getTag();
        Intent detailIntent = new Intent(BuyingActivity.this, ItemDetailActivity.class);
        detailIntent.putExtra("ProductID", productID);
        startActivity(detailIntent);
    }
}