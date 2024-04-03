package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class PlaceOrder_Activity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        databaseHelper = new DatabaseHelper(this);

        EditText edNameOnCard = findViewById(R.id.edNameOnCard);
        EditText edCardNum = findViewById(R.id.edCardNum);
        EditText edCVV = findViewById(R.id.edCVV);
        EditText edExpDate = findViewById(R.id.edExpDate);
        Button btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        Intent intent = getIntent();
        if (intent.hasExtra("OrderID")) {
            orderId = intent.getIntExtra("OrderID", -1);
        }

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (databaseHelper != null) {
                    int orderId = getIntent().getIntExtra("OrderID", -1);
                    Log.d("PlaceOrder", "getting orderId: " + orderId);
                    String newStatus = "Payment Processed";

                    boolean success = databaseHelper.updateOrderStatus(orderId, newStatus);
                    ProductModel pm = databaseHelper.getOrder(orderId);
                    if (success) {
                        //set up notification title and message
                        Data.Builder notificationMessage = new Data.Builder();
                        String method = getIntent().getStringExtra("method");
                        if(method != null){
                            switch(method){
                                case"pickup":
                                    notificationMessage.putString("title", "Your order is ready for pick up");
                                    notificationMessage.putString("content", String.format("%s is ready for pick up at %s", pm.getProductName(), pm.getPickupAddress()));
                                    break;
                                case"delivery":
                                    notificationMessage.putString("title", "Your order is ready to ship");
                                    notificationMessage.putString("content", String.format("%s is ready to ship", pm.getProductName()));
                                    break;
                            }
                        }
                        Data data = notificationMessage.build();
                        //Send notification after 5 seconds
                        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationHelper.class)
                                .setInputData(data)
                                .setInitialDelay(5, TimeUnit.SECONDS)
                                .build();
                        WorkManager.getInstance(PlaceOrder_Activity.this).enqueue(notificationWork);
                        startActivity(new Intent(PlaceOrder_Activity.this, PaymentProcessed.class));
                    } else {
                        Toast.makeText(PlaceOrder_Activity.this, "Failed to update order status.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(PlaceOrder_Activity.this, "DatabaseHelper is null.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}