package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        databaseHelper = new DatabaseHelper(this);


//        Button view = findViewById(R.id.btnView);
        ListView lv = findViewById(R.id.lvOrders);
        ArrayList<OrderModel> orderModelArrayList = getOrderHistory();

//        String[] sample = {"product A", "product B"};
//        orderModelArrayList.add(new OrderModel(001L, sample, "03-16-2024", "delivered"));

        OrderDetailLVAdapter adapter = new OrderDetailLVAdapter(this, orderModelArrayList);
        lv.setAdapter((adapter));

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

    public void onClickView(View view){
        startActivity(new Intent(OrderHistoryActivity.this, OrderDetailActivity.class));
    }

}