package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText regUsername, regPhone, regPassword, conPassword;
    Button btnRegister;

    MedPalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Edit Text
        regUsername = (EditText)findViewById(R.id.registerUserName);
        regPhone = (EditText)findViewById(R.id.regPhone);
        regPassword = (EditText)findViewById(R.id.regPassword);
        conPassword = (EditText)findViewById(R.id.regConfirmPass);

        db = new MedPalDatabase(Register.this);

        //Button
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringUsername = regUsername.getText().toString();
                String stringPhone = regPhone.getText().toString();
                String stringPassword = regPassword.getText().toString();
                String stringConPassword = conPassword.getText().toString();
                if (stringUsername.equals("")||stringPhone.equals("")||stringPassword.equals("")||stringConPassword.equals("")) {
                    Toast.makeText(Register.this,"Please enter registration details", Toast.LENGTH_SHORT).show();
                } else {
                    if (stringPassword.equals(stringConPassword)) {
                        Boolean checkUsername = db.checkUsername(stringUsername);
                        if (checkUsername == true) {
                            Boolean insertUserData = db.insertUserData(stringUsername, stringPhone, stringPassword, stringConPassword);
                            if (insertUserData == true) {
                                Intent homePageIntent = new Intent(Register.this, HomePage.class);
                                startActivity(homePageIntent);
                                Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }
        });
    }
}
