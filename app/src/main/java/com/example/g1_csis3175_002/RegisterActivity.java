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
        Spinner city = findViewById(R.id.spCity);
        Button btRegister = findViewById(R.id.btCreatAcc);
        databaseHelper = new DatabaseHelper(this);

        btRegister.setOnClickListener(new View.OnClickListener() {
            String isInserted;
            @Override
            public void onClick(View v) {
                //addUser(String username, String name, String address, String zipcode, String city,
                //int contact, String email, String password)
                isInserted = databaseHelper.addUser(
                        username.getText().toString(),
                        name.getText().toString(),
                        address.getText().toString(),
                        zipcode.getText().toString(),
                        city.getSelectedItem().toString(),
                        Integer.parseInt(phone.getText().toString()),
                        email.getText().toString(),
                        password.getText().toString()
                );

                switch (isInserted) {
                    case UserMessage.SUCCESS:
                        Toast.makeText(RegisterActivity.this, "You are registered.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        break;
                    case UserMessage.EMAIL_EXISTS:
                        Toast.makeText(RegisterActivity.this, "This email already exists", Toast.LENGTH_LONG).show();
                        break;
                    case UserMessage.USERNAME_EXISTS:
                        Toast.makeText(RegisterActivity.this, "This username already exists", Toast.LENGTH_LONG).show();
                        break;
                    case UserMessage.ERROR:
                        Toast.makeText(RegisterActivity.this, "Not able to register", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(RegisterActivity.this, "Unexpected error", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
}