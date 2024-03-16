package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.btnLogin);
        login.setOnClickListener(this::onClickLogin);


    }
    public void onClickSignUp(View view){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void onClickLogin(View view){
        /*
        should perform user authentication prior to direct them to Home page
        will implement authentication after db is set
         */
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
}