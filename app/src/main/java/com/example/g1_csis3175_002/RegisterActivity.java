package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText name = findViewById(R.id.edName);
        EditText username = findViewById(R.id.edUsername);
        EditText address = findViewById(R.id.edAddress);
        EditText phone = findViewById(R.id.edPhone);
        EditText email = findViewById(R.id.edCreateEmail);
        EditText password = findViewById(R.id.edCreatPassword);
        Button btTest = (Button) findViewById(R.id.btTest);
        Button btRegister = (Button) findViewById(R.id.btCreatAcc);
        databaseHelper = new DatabaseHelper(this);
        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, TestingPages.class));
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View v) {
                isInserted = databaseHelper.addRecord(name.getText().toString(),
                        username.getText().toString(),
                        address.getText().toString(),
                        Integer.parseInt(phone.getText().toString()),
                        email.getText().toString(),
                        password.getText().toString()
                );

                if(isInserted)
                    Toast.makeText(RegisterActivity.this, "You are registered.",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RegisterActivity.this,
                            "Not able to register", Toast.LENGTH_LONG).show();
            }
        });
    }
}