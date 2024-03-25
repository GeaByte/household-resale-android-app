package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Instantiate DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Retrieve product data from the database
        ArrayList<ProductModel> productList = dbHelper.getAllProducts();

        Button btnEdit = findViewById(R.id.btnEdit);
        TextView id = findViewById(R.id.tvShowOrderId);
        TextView itemDetailTextView = findViewById(R.id.tvItemDetail);
        TextView deliveryOption = findViewById(R.id.tvShowDeliveryOption);
        TextView status = findViewById(R.id.tvShowOrderStatus);
        ImageView productImage = findViewById(R.id.imgOrderDetailProduct);

        if (!productList.isEmpty()) {
            // Assuming you want to load the first product into the layout
            ProductModel product = productList.get(0);

            // Set product details to views
            id.setText(String.valueOf(product.getOrderId())); // Assuming getOrderId() returns order ID
            itemDetailTextView.setText(product.getItemDetail());
            deliveryOption.setText(product.getDeliveryOption());
            status .setText(product.getOrderStatus());


            // Load product image using Glide or any other image loading library
            // Assuming productImagePath is the path to your image
            Uri imageUri = Uri.parse(product.getImagePath());
            productImage.setImageURI(imageUri);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailActivity.this,Edit_Order.class));
            }
        });

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