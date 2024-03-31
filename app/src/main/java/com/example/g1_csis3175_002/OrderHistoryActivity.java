package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        databaseHelper = new DatabaseHelper(this);


//        Button view = findViewById(R.id.btnView);
        ListView lv = findViewById(R.id.lvOrders);
        ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();

        String[] sample = {"product A", "product B"};
        orderModelArrayList.add(new OrderModel(001L, sample, "03-16-2024", "delivered"));


        ListView listView = findViewById(R.id.lvOrders);

        // Retrieve data from the database and populate the ArrayList
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<OrderModel> orders = dbHelper.getAllOrders();

        // Create custom adapter and set it to the ListView
        OrderDetailLVAdapter adapter = new OrderDetailLVAdapter(this, orders);
        listView.setAdapter(adapter);









        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderModel selectedOrder = orderModelArrayList.get(position);
                if (selectedOrder != null) {
                    Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
                    intent.putExtra("ORDER_ID", selectedOrder.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(OrderHistoryActivity.this, "Error: Order not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private ArrayList<OrderModel> getOrderHistory() {
        ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();
        // Retrieve order history data from the database using dbHelper
        Cursor cursor = databaseHelper.viewOrder();

        // Process the cursor data and populate the orderModelArrayList
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int orderId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL1));
//                String shippingAddress = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL2));
                String orderDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL3));
                String orderStatus = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL4));
                int orderItem = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL5));

                OrderModel order = new OrderModel(orderId, orderItem, orderDate, orderStatus);
                orderModelArrayList.add(order);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return orderModelArrayList;
    }

    public void onClickEdit(View view){

        startActivity(new Intent(OrderHistoryActivity.this, Edit_Order.class));
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
















}