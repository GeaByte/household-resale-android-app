package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuyingActivity extends AppCompatActivity {
    GridView productGV;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);
        databaseHelper = new DatabaseHelper(this);


        SearchView searchView = findViewById(R.id.searchView);

        productGV = findViewById(R.id.GVbuying);
        ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
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