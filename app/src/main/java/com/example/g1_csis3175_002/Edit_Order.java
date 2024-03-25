package com.example.g1_csis3175_002;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Edit_Order extends AppCompatActivity {


    // Define views
    private EditText txtShippingAddress;
    private Button btnSaveEdit;

    // DatabaseHelper instance
    private DatabaseHelper dbHelper;

    // Order ID
    private int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_order);

        ImageView goBack = findViewById(R.id.btnBackToOrder);



        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit_Order.this,OrderDetailActivity.class));
            }
        });
        // Initialize views
        txtShippingAddress = findViewById(R.id.txtItemDetail);
        btnSaveEdit = findViewById(R.id.btnSaveEdit);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Get order ID from intent
        orderId = getIntent().getIntExtra("ORDER_ID", -1);

        // Populate shipping address EditText with existing address
        String existingAddress = getIntent().getStringExtra("SHIPPING_ADDRESS");
        txtShippingAddress.setText(existingAddress);

        // Set onClickListener for btnSaveEdit
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get updated shipping address
                String newAddress = txtShippingAddress.getText().toString().trim();

                // Update shipping address in database
                boolean isUpdated = dbHelper.updateShippingAddress(orderId, newAddress);

                if (isUpdated) {
                    // If update successful, show a toast message
                    Toast.makeText(Edit_Order.this, "Shipping address updated successfully", Toast.LENGTH_SHORT).show();
                    // Finish the activity
                    finish();
                } else {
                    // If update failed, show a toast message
                    Toast.makeText(Edit_Order.this, "Failed to update shipping address", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }
}