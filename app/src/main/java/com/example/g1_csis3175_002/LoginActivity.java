package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.btnLogin);
        email = findViewById(R.id.edEmail);
        password = findViewById(R.id.edPassword);
        databaseHelper = new DatabaseHelper(this);
        login.setOnClickListener(this::onClickLogin);

    }
    public void onClickSignUp(View view){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void onClickLogin(View view){
        String result = databaseHelper.loginUser(
                email.getText().toString(),
                password.getText().toString()
        );

        switch (result) {
            case UserMessage.SUCCESS:
                Toast.makeText(LoginActivity.this, "Login", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                break;
            case UserMessage.ERROR:
                Toast.makeText(LoginActivity.this, "The user not exists or password not correct", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(LoginActivity.this, "Unexpected error", Toast.LENGTH_LONG).show();
                break;
        }
    }
}