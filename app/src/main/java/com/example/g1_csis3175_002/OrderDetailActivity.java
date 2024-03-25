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

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {


    private TextView txtShippingAddress;
    private TextView tvShowOrdate;
    private TextView tvShowOrderStatus;

    // DatabaseHelper instance
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Button btnEdit = findViewById(R.id.btnEdit);
        ImageView productImage = findViewById(R.id.imgOrderDetailProduct);

        // Initialize TextViews
        txtShippingAddress = findViewById(R.id.txtShippingAddress);
        tvShowOrdate = findViewById(R.id.tvShowOrdate);
        tvShowOrderStatus = findViewById(R.id.tvShowOrderStatus);

        // Get order ID from intent or any other source
        int orderId = getIntent().getIntExtra("ORDER_ID", -1);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);


        // Get order details
        Cursor cursor = dbHelper.getOrderDetails(orderId);

        // Move the cursor to the first row
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve data from the cursor
            String shippingAddress = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL2));
            String orderDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL3));
            String orderStatus = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL4));

            // Populate TextViews with the retrieved data
            txtShippingAddress.setText(shippingAddress);
            tvShowOrdate.setText(orderDate);
            tvShowOrderStatus.setText(orderStatus);

            // Close the cursor
            cursor.close();
        }




        String statusOrder = tvShowOrderStatus.getText().toString();
        /*String statusOrder = "Deliveried"; //test */

        if (statusOrder != "Deliveried"){
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(OrderDetailActivity.this,Edit_Order.class));
                }
            });
        }
        else
            btnEdit.setText("Report");


        /*
        sample data
         */

        /*id.setText("001");
        deliveryOption.setText("Delivery");
        status.setText("Selling");
        productImage.setImageResource(R.drawable.logo);*/

        //check order status
        //if delivered: change button to report
        //if not delivered: keep it as edit
    }
}