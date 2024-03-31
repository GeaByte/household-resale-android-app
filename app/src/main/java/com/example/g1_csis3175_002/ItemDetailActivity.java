package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ItemDetailActivity extends AppCompatActivity {
    private ProductModel product;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        TextView tvPName = findViewById(R.id.txtProductName);
        TextView tvDiscount = findViewById(R.id.txtProductPrice);
        TextView txtSellerInfo = findViewById(R.id.txtSellerInfo);
        TextView txtUserShipAd = findViewById(R.id.txtPickupAd);
        TextView txtPickupTitle = findViewById(R.id.txtPickupAddress);
        TextView tvDes = findViewById(R.id.txtDescription);
        ImageView img = findViewById(R.id.imgProductItemDetail);
        Button btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        RadioGroup rdbtnGroup = findViewById(R.id.rdbtnGroup);
        RadioButton rdbtnPickup = findViewById(R.id.rdbtnPickup);
        RadioButton rdbtnDelivery = findViewById(R.id.rdbtnDelivery);



        int productID = getIntent().getIntExtra("ProductID", -1);

        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(this::onClickBack);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        product = databaseHelper.getProduct(productID);


        tvPName.setText(product.getProductName());
        tvDiscount.setText(String.format(Locale.getDefault(), "$%.2f", product.getPrice()));
        tvDes.setText(product.getDescription());
        txtSellerInfo.setText(product.getSeller());
        txtUserShipAd.setText(product.getPickupAddress());

        rdbtnGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdbtnPickup) {
                    txtUserShipAd.setVisibility(View.VISIBLE);
                    txtPickupTitle.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rdbtnDelivery) {

                    txtUserShipAd.setVisibility(View.GONE);
                    txtPickupTitle.setVisibility(View.GONE);
                }
            }
        });

        Glide.with(this)
                .load(product.getImagePath())
                .into(img);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int orderId = generateRandomOrderId();
//                String address = txtUserShipAd.getText().toString();
//                String orderDate = getCurrentDate();
//                String orderStatus = "Pending";
//                ProductModel product = databaseHelper.getProduct(productID);
//
//                boolean orderPlaced = databaseHelper.addOrder(orderId, address, orderDate, orderStatus, productID);
//                if (orderPlaced) {
//                    Toast.makeText(ItemDetailActivity.this,
//                            "Order successfully created.", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(ItemDetailActivity.this,
//                            "Order was not created.", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    public static int generateRandomOrderId() {
        Random random = new Random();

        int orderId = random.nextInt(9000) + 1000;

        return orderId;
    }
    public static String getCurrentDate() {

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        return date;
    }



    public void onClickBack(View view){
        startActivity(new Intent(ItemDetailActivity.this, BuyingActivity.class));
    }
}