package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
                String username = databaseHelper.getUsernameByEmail(email.getText().toString());
                if (username != null) {
                    saveLoggedInInfo(username, email.getText().toString());
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Username could not be found", Toast.LENGTH_LONG).show();
                }
                break;
            case UserMessage.ERROR:
                Toast.makeText(LoginActivity.this, "The user not exists or password not correct", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(LoginActivity.this, "Unexpected error", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void saveLoggedInInfo(String username, String email) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.apply();
    }
}