package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Button btnEdit = findViewById(R.id.btnEdit);
        TextView id = findViewById(R.id.tvShowOrderId);
        TextView deliveryOption = findViewById(R.id.tvShowDeliveryOption);
        TextView status = findViewById(R.id.tvShowOrderStatus);
        ImageView productImage = findViewById(R.id.imgOrderDetailProduct);

        /*
        sample data
         */

        id.setText("001");
        deliveryOption.setText("Delivery");
        status.setText("Selling");
        productImage.setImageResource(R.drawable.logo);

        //check order status
        //if delivered: change button to report
        //if not delivered: keep it as edit
    }
}