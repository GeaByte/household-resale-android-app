package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

//        Button view = findViewById(R.id.btnView);




 /*       ListView listView = findViewById(R.id.lvOrders);

        // Retrieve data from the database and populate the ArrayList
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<OrderModel> orders = dbHelper.getAllOrders();

        // Create custom adapter and set it to the ListView
        OrderDetailLVAdapter adapter = new OrderDetailLVAdapter(this, orders);
        listView.setAdapter(adapter);*/


        ListView listView = findViewById(R.id.lvOrders);

        // Retrieve data from the database and populate the ArrayList
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<OrderModel> orders = dbHelper.getAllOrders();

        // Create custom adapter and set it to the ListView
        OrderDetailLVAdapter adapter = new OrderDetailLVAdapter(this, orders);
        listView.setAdapter(adapter);

        // Set click listener for items in the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the clicked item
                OrderModel clickedOrder = orders.get(position);

                // Start OrderDetailActivity and pass the order ID as an extra
                Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
                intent.putExtra("ORDER_ID", clickedOrder.getId());
                startActivity(intent);
            }
        });

    }
    public void onClickView(View view) {
        // Handle button click here
    }



   /* public void onClickView(View view) {

        startActivity(new Intent(OrderHistoryActivity.this, OrderDetailActivity.class));

    }





    public void onClickEdit(View view){
        //direct to edit order page
    }*/
}