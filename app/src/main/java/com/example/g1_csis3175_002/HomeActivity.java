package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity {
    String usernamePreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView profileIcon = findViewById(R.id.imgProfile);
        TextView username = findViewById(R.id.tvUsername);
        TextView userEmail = findViewById(R.id.tvUserEmail);
        Button buyItem = findViewById(R.id.btnBuyItem);
        Button sellItem = findViewById(R.id.btnSellItem);
        Button viewOrderHistory = findViewById(R.id.btnViewOrderHistory);
        Button logout = findViewById(R.id.btnLogout);
        Button viewEditProfile = findViewById(R.id.btnEditProfile);
        Button viewSellerItem = findViewById(R.id.btnViewSellingItem);
        Button btnShoppingCart = findViewById(R.id.btnShoppingCart);
        btnShoppingCart.setOnClickListener(this::onClickShoppingCart);

        viewEditProfile.setOnClickListener(this::onClickViewEditProfile);
        viewOrderHistory.setOnClickListener(this::onClickViewOrderHistory);
        sellItem.setOnClickListener(this::onClickSellAnItem);
        buyItem.setOnClickListener(this::onClickBuyAnItem);
        logout.setOnClickListener(this::onClickLogout);
        viewSellerItem.setOnClickListener(this::onCLickViewSellingItem);

        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        usernamePreferences = sharedPref.getString("username", "");
        String emailPreferences = sharedPref.getString("email", "");

        profileIcon.setImageResource(R.drawable.buyer);
        username.setText(usernamePreferences);
        userEmail.setText(emailPreferences);

        //check for notification permission
        if (!NotificationHelper.isNotificationPermissionGranted(this)){
            //if not, ask for permission
            NotificationHelper.showPermissionDialog(this);
        }
    }

    public void onClickBuyAnItem(View view){
        startActivity(new Intent(HomeActivity.this, BuyingActivity.class));
    }

    public void onClickSellAnItem(View view){
        startActivity(new Intent(HomeActivity.this, ListingItemActivity.class));
    }

    public void onClickViewOrderHistory(View view){
        startActivity(new Intent(HomeActivity.this, OrderHistoryActivity.class));
    }


    public void onClickLogout(View view){
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void onClickViewEditProfile(View view){
        Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
        intent.putExtra("update", true);
        intent.putExtra("username", usernamePreferences);
        startActivity(intent);
    }

    public void onCLickViewSellingItem(View view){
        startActivity(new Intent(HomeActivity.this, ViewSellingItemActivity.class));
    }

    public void onClickShoppingCart(View view){
        startActivity(new Intent(HomeActivity.this, Cart_Activity.class));
    }
}