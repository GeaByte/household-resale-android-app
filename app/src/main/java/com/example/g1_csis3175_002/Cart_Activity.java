package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class Cart_Activity extends AppCompatActivity{


    DatabaseHelper databaseHelper;
    GridView cartItems;
    TextView txtCartTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        databaseHelper = new DatabaseHelper(this);
        cartItems = findViewById(R.id.cartGV);
        int orderId = getIntent().getIntExtra("OrderID", -1);
        int productId = getIntent().getIntExtra("ProductID", -1);
        Button btnCheckOut= findViewById(R.id.btnCheckOut);
        Button btnContinueShopping = findViewById(R.id.btnContinueShopping);
        txtCartTotal = findViewById(R.id.txtCartTotal);


        ArrayList<ProductModel> cartItemList = databaseHelper.getAllCartItems();

        double cartTotal = 0.0;
        for (ProductModel item : cartItemList) {
            cartTotal += item.getPrice(); // To sum prices
        }

        txtCartTotal.setText(String.format("Total: $%.2f", cartTotal));
        CartGVAdapter adapter = new CartGVAdapter(this, cartItemList, new DatabaseHelper(this));

        cartItems.setAdapter(adapter);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart_Activity.this, PlaceOrder_Activity.class);
                intent.putExtra("OrderID", orderId);
                startActivity(intent);
            }
        });

        btnContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cart_Activity.this, BuyingActivity.class));
            }
        });

    }

}