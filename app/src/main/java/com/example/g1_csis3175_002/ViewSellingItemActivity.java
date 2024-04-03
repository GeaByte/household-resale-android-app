package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewSellingItemActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private ArrayList<ProductModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selling_item);

        db = new DatabaseHelper(this);
        Button back = findViewById(R.id.btnSellingItemBack);
        ListView lv = findViewById(R.id.lvSellingItems);

        //set back button function
        back.setOnClickListener(this::onClickBack);

        //get selling product data
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(ViewSellingItemActivity.this);
        String username = sharedPref.getString("username", "");
        list = db.getUserProducts(username);
        //set listview
        ArrayAdapter<ProductModel> productModelArrayAdapter = new SellingItemLVAdapter(this, list);
        lv.setAdapter(productModelArrayAdapter);
    }

    public void onClickBack(View view){
        startActivity(new Intent(ViewSellingItemActivity.this, HomeActivity.class));
    }
}