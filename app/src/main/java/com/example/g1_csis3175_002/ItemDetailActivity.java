package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.Locale;

public class ItemDetailActivity extends AppCompatActivity {
    private ProductModel product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        TextView tvPName = findViewById(R.id.textView);
        TextView tvPrice = findViewById(R.id.textView2);
        TextView tvDiscount = findViewById(R.id.textView3);
        TextView tvSellerInfo = findViewById(R.id.textView4);
        TextView tvSeller = findViewById(R.id.textView5);
        TextView tvCon = findViewById(R.id.textView6);
        TextView tvDes = findViewById(R.id.textView7);
        ImageView img = findViewById(R.id.imgProductItemDetail);



        int productID = getIntent().getIntExtra("ProductID", -1);

        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(this::onClickBack);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        product = databaseHelper.getProduct(productID);

        tvPName.setText(product.getProductName());
        tvDiscount.setText(String.format(Locale.getDefault(), "$%.2f", product.getPrice()));
        tvDes.setText(product.getDescription());
        Glide.with(this)
                .load(product.getImagePath())
                .into(img);
    }

    public void onClickBack(View view){
        /*
        need to implement Back button to previous browsing stage
        not from top again
         */
        startActivity(new Intent(ItemDetailActivity.this, BuyingActivity.class));
    }
}