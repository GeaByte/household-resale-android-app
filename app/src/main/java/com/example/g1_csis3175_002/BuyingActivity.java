package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class BuyingActivity extends AppCompatActivity {
    GridView productGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);

        productGV = findViewById(R.id.GVbuying);
        ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
        /*
        testing buying layout with some sample products
        will change this part after figure out how to get data from database
         */
        productModelArrayList.add(new ProductModel("Make some money", R.drawable.buyer, 19.99));
        productModelArrayList.add(new ProductModel("kitchenware", R.drawable.img1, 5.99));
        productModelArrayList.add(new ProductModel("spending", R.drawable.seller, 9.99));

        ProductGVAdapter adapter = new ProductGVAdapter(this, productModelArrayList);
        productGV.setAdapter((adapter));
    }

    public void onClickSelect(View view){
        startActivity(new Intent(BuyingActivity.this, ItemDetailActivity.class));
    }
}