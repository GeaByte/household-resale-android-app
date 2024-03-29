package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class OrderDetailActivity extends AppCompatActivity {


    private TextView txtShippingAddress;
    private TextView tvShowOrdate;
    private TextView tvShowOrderStatus;
    private TextView tvShowOrderID;

    // DatabaseHelper instance
    private DatabaseHelper dbHelper;
    private ProductModel product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        ImageView productImage = findViewById(R.id.imgOrderDetailProduct);
        TextView orderIdTextView = findViewById(R.id.tvShowOrderId);
        TextView shippingAddressTextView = findViewById(R.id.txtShowAddress);
        TextView orderDateTextView = findViewById(R.id.tvShowOrdate);
        TextView orderStatusTextView = findViewById(R.id.tvShowOrderStatus);



        // Retrieve the order ID from the intent extras
        int orderId = getIntent().getIntExtra("ORDER_ID", -1); // Example: retrieving order ID

        // Retrieve the order details from the database based on the order ID
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        OrderModel order = dbHelper.getOrderById(orderId);

        // Populate the views with the order details
        if (order != null) {

            // Example: Assuming OrderModel has methods to retrieve product image, shipping address, order date, and status
            /*productImage.setImageResource(order.getStatus());*/
            orderIdTextView.setText(String.valueOf(order.getId()));
            shippingAddressTextView.setText(order.getStatus());
            orderDateTextView.setText(order.getDate());
            orderStatusTextView.setText(order.getStatus());

        }
    }
}