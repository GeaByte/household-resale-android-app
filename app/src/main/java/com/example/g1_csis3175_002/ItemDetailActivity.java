package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(this::onClickBack);
    }

    public void onClickBack(View view){
        /*
        need to implement Back button to previous browsing stage
        not from top again
         */
        startActivity(new Intent(ItemDetailActivity.this, BuyingActivity.class));
    }
}