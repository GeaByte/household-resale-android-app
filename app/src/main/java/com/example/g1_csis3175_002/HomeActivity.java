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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //declare and initiate variables
        ImageView profileIcon = findViewById(R.id.imgProfile);
        TextView username = findViewById(R.id.tvUsername);
        TextView userEmail = findViewById(R.id.tvUserEmail);
        Button buyItem = findViewById(R.id.btnBuyItem);
        Button sellItem = findViewById(R.id.btnSellItem);
        Button viewEditOrder = findViewById(R.id.btnViewOrderHistory);
        Button viewOrderHistory = findViewById(R.id.btnViewOrderHistory);
        Button logout = findViewById(R.id.btnLogout);

        //set button listeners
        buyItem.setOnClickListener(this::onClickBuyAnItem);
        sellItem.setOnClickListener(this::onClickSellAnItem);
        viewEditOrder.setOnClickListener(this::onClickViewOrderHistory);
        viewOrderHistory.setOnClickListener(this::onClickViewOrderHistory);
        logout.setOnClickListener(this::onClickLogout);

        //sharedPreference storage
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String usernamePreferences = sharedPref.getString("username", "");
        String emailPreferences = sharedPref.getString("email", "");

        profileIcon.setImageResource(R.drawable.buyer);
        username.setText(usernamePreferences);
        userEmail.setText(emailPreferences);

        //check for notification permission
        if (!NotificationHelper.isNotificationPermissionGranted(this)){
            //if not, ask for permission
            NotificationHelper.showPermissionDialog(this);
        }

        /*
        * Move this part to after purchase
        * */
        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationHelper.class)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(this).enqueue(notificationWork);
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
        /*
        not sure if we need to do something to logout like session_destroy() in php.
         */
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }
}