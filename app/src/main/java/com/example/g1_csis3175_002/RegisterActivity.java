package com.example.g1_csis3175_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText name;
    EditText username;
    EditText address;
    EditText zipcode;
    EditText phone;
    EditText email;
    EditText password;
    Spinner spCity;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView title = findViewById(R.id.txRegisterTitle);
        name = findViewById(R.id.edName);
        username = findViewById(R.id.edUsername);
        address = findViewById(R.id.edStreet);
        zipcode = findViewById(R.id.edZipCode);
        phone = findViewById(R.id.edPhone);
        email = findViewById(R.id.edCreateEmail);
        password = findViewById(R.id.edCreatPassword);
        spCity = (Spinner) findViewById(R.id.spCity);
        btRegister = (Button) findViewById(R.id.btCreatAcc);
        databaseHelper = new DatabaseHelper(this);
        boolean isUpdate = getIntent().getBooleanExtra("update", false);

        if(isUpdate){
            //profile update
            title.setText(R.string.txtUpdateYourAccount);
            btRegister.setText(R.string.btnProfileUpdate);
            btRegister.setOnClickListener(this::onClickUpdate);
        }else{
            //register new profile
            title.setText(R.string.txRegisterDesc);
            btRegister.setText(R.string.btRegister);
            btRegister.setOnClickListener(this::onClickRegister);
        }
    }
    //register account
    public void onClickRegister(View view){
        String isInserted;
        String city = spCity.getSelectedItem().toString();
        isInserted = databaseHelper.addUser(
                username.getText().toString(),
                name.getText().toString(),
                address.getText().toString(),
                zipcode.getText().toString(),
                city,
                Integer.parseInt(phone.getText().toString()),
                email.getText().toString(),
                password.getText().toString()
        );
        isInserted(isInserted);
    }

    //update profile
    public void onClickUpdate(View view){
        boolean isInserted;
        String city = spCity.getSelectedItem().toString();
        isInserted = databaseHelper.updateUser(
                username.getText().toString(),
                name.getText().toString(),
                address.getText().toString(),
                zipcode.getText().toString(),
                city,
                Integer.parseInt(phone.getText().toString()),
                email.getText().toString(),
                password.getText().toString()
        );
        if (isInserted){
            Toast.makeText(RegisterActivity.this, "Profile is updated.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }else{
            Toast.makeText(RegisterActivity.this, "Profile is not update.", Toast.LENGTH_LONG).show();
        }
    }

    //check if insert successful
    public void isInserted(String isInserted){
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
}