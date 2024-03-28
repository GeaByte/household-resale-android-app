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

        Button btnEdit = findViewById(R.id.btnEdit);
        ImageView productImage = findViewById(R.id.imgOrderDetailProduct);

        // Initialize TextViews
        txtShippingAddress = findViewById(R.id.txtShowAddress);
        tvShowOrdate = findViewById(R.id.tvShowOrdate);
        tvShowOrderStatus = findViewById(R.id.tvShowOrderStatus);
        tvShowOrderID = findViewById(R.id.tvShowOrderId);


        // Get order ID from intent or any other source

        int productID = getIntent().getIntExtra("ProductID", 1);


        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        product = databaseHelper.getProduct2(productID);



        tvShowOrderID.setText(String.valueOf(product.getProductID()+001));

        txtShippingAddress.setText(product.getProductName());
        /*tvShowOrdate.setText(String.format(Locale.getDefault(), "$%.2f", product.getPrice()));*/
        /*tvShowOrdate.setText(String.valueOf(product.getOrderDate()));*/

        Date orderDate = product.getOrderDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(orderDate);
        tvShowOrdate.setText(formattedDate);
        tvShowOrderStatus.setText(product.getDescription());
        Glide.with(this)
                .load(product.getImagePath())
                .into(productImage);

      /*  // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);


        // Get order details
        Cursor cursor = dbHelper.getOrderDetails(orderId);*/

        // Move the cursor to the first row
      /*  if (cursor != null && cursor.moveToFirst()) {
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
        }*/




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