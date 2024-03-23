package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText name = findViewById(R.id.edName);
        EditText username = findViewById(R.id.edUsername);
        EditText address = findViewById(R.id.edStreet);
        EditText zipcode = findViewById(R.id.edZipCode);
        EditText phone = findViewById(R.id.edPhone);
        EditText email = findViewById(R.id.edCreateEmail);
        EditText password = findViewById(R.id.edCreatPassword);
        Spinner spCity = (Spinner) findViewById(R.id.spCity);
        Button btRegister = (Button) findViewById(R.id.btCreatAcc);
        databaseHelper = new DatabaseHelper(this);

        btRegister.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View v) {
                String city = spCity.getSelectedItem().toString();
                isInserted = databaseHelper.addUser(name.getText().toString(),
                        username.getText().toString(),
                        address.getText().toString(),
                        zipcode.getText().toString(),
                        city,
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