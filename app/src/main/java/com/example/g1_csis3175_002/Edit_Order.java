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
        Spinner option = findViewById(R.id.spnDiliOption);
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
                String optionChoose = option.getSelectedItem().toString();


                if (optionChoose.equals("Choose Delivery Option")){
                    Toast.makeText(Edit_Order.this,
                            "Plsease Choose Delivery Option",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(Edit_Order.this,
                            "Saved Your Edit",Toast.LENGTH_LONG).show();

                Integer.parseInt(orderID.getText().toString());



            }
        });


    }
}