package com.example.g1_csis3175_002;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_order);

        Button btnSave = findViewById(R.id.btnSaveEdit);
        TextView option = findViewById(R.id.txtDiliOption);
        TextView orderID = findViewById(R.id.txtOrderID);
        TextView itemDetail = findViewById(R.id.txtItemDetail);

        TextView status = findViewById(R.id.txtStatus);
        ImageView goBack = findViewById(R.id.btnBackToOrder);



        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit_Order.this,OrderDetailActivity.class));
            }
        });





        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });


    }
}