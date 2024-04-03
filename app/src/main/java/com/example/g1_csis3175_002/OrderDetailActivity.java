package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OrderDetailActivity extends AppCompatActivity {



    // DatabaseHelper instance
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Button btnBack = findViewById(R.id.btnBackward);
        ImageView productImage = findViewById(R.id.imgOrderDetailProduct);
        TextView orderID = findViewById(R.id.tvShowOrderId);
        TextView price = findViewById(R.id.txtShowPrice);
        TextView orderDate = findViewById(R.id.tvShowOrdate);
        TextView status = findViewById(R.id.tvShowOrderStatus);
        TextView typeOfService = findViewById(R.id.tvShowService);


        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Get order ID from intent extra
        Intent intent = getIntent();
        int orderId = intent.getIntExtra("ORDER_ID", -1);

        // Get OrderModel object corresponding to the order ID
        OrderModel order = dbHelper.getOrderById(orderId);



        // Populate views with order details
        if (order != null) {
            orderID.setText(String.valueOf(order.getId()));
            price.setText(String.valueOf(order.getPrice()));
            orderDate.setText(order.getDate());
            status.setText(order.getStatus());
            typeOfService.setText(order.getTypeOfService());
            // Set product image using Glide or any other image loading library
            Glide.with(this).load(order.getProductImagePath()).into(productImage);
        }

        // Set onClickListener for back button
        btnBack.setOnClickListener(v -> onBackPressed());
    }







      /*  btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailActivity.this,OrderHistoryActivity.class));
            }
        });*/






    }
