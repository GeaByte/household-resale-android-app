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

        // Set item click listener to the ListView
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                OrderModel clickedOrder = (OrderModel) parent.getItemAtPosition(position);
//                int orderId = clickedOrder.getId();
//
//
//                // Start OrderDetailActivity and pass the order ID
//                Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
//                intent.putExtra("ORDER_ID", orderId);
//                startActivity(intent);
//            }
//        });


    }



    public void onClickView(View view) {


        startActivity(new Intent(OrderHistoryActivity.this, OrderDetailActivity.class));


    }

    public void onCancelClick(View view) {
        // Find the parent row view
        View parentRow = (View) view.getParent();

        // Directly use the listView reference since we are in the same activity
        ListView listView = findViewById(R.id.lvOrders);

        // Get the position of the clicked button's parent layout in the ListView
        int position = listView.getPositionForView(parentRow);

        // Get the corresponding OrderModel object
        OrderModel order = (OrderModel) listView.getAdapter().getItem(position);

        if (order.getStatus().equalsIgnoreCase("Delivered")) {
            Toast.makeText(this, "Cancellation is not allowed for delivered orders", Toast.LENGTH_SHORT).show();
        } else {
            // Assuming you have access to delete the order directly
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            dbHelper.deleteOrder(order.getId());

            // Update the adapter and ListView
            ((OrderDetailLVAdapter) listView.getAdapter()).remove(order);
            ((OrderDetailLVAdapter) listView.getAdapter()).notifyDataSetChanged();
            Toast.makeText(this, "Order with ID " + order.getId() + " has been canceled", Toast.LENGTH_SHORT).show();
        }
    }









    public void onClickEdit(View view){
        //direct to edit order page
    }
}