package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    int position;

    OrderModel or = new OrderModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

//        Button view = findViewById(R.id.btnView);




        ListView listView = findViewById(R.id.lvOrders);

        // Retrieve data from the database and populate the ArrayList
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<OrderModel> orders = dbHelper.getAllOrders();

        // Create custom adapter and set it to the ListView
        OrderDetailLVAdapter adapter = new OrderDetailLVAdapter(this, orders);
        listView.setAdapter(adapter);









    }

    public void onCancelClick(View view) {
        // Find the parent ListView item containing the button
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();

        // Get the position of the button within the ListView
        int position = listView.getPositionForView(parentRow);

        // Get the corresponding OrderModel object
        OrderModel order = (OrderModel) listView.getAdapter().getItem(position);

        // Check if the order status is "Delivered"
        if (order.getStatus().equalsIgnoreCase("Delivered")) {
            // Show a message that cancellation is not allowed for delivered orders
            Toast.makeText(this, "Cancellation is not allowed for delivered orders", Toast.LENGTH_SHORT).show();
        } else {
            // Remove the order from the database
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            dbHelper.deleteOrder(order.getId());

            // Remove the order from the ArrayList used by the adapter
            ((OrderDetailLVAdapter) listView.getAdapter()).remove(order);

            // Notify the adapter that the data set has changed
            ((OrderDetailLVAdapter) listView.getAdapter()).notifyDataSetChanged();

            // Show a toast message indicating the order has been canceled
            Toast.makeText(this, "Order with ID " + order.getId() + " has been canceled", Toast.LENGTH_SHORT).show();
        }
    }
















    public void onClickEdit(View view){
        //direct to edit order page
    }
}