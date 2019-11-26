package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    Button btnSignIn;
    EditText log_in_username, log_in_password;

    MedPalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        log_in_username = (EditText)findViewById(R.id.editTextUsername);
        log_in_password = (EditText)findViewById(R.id.editTextPassword);

        db = new MedPalDatabase(LogIn.this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logInUsername = log_in_username.getText().toString();
                String logInPassword = log_in_password.getText().toString();
                Boolean checkLoginDetails = db.checkLoginDetails(logInUsername, logInPassword);
                if (checkLoginDetails) {
                    Intent homePageIntent = new Intent(LogIn.this, HomePage.class);
                    startActivity(homePageIntent);
                    Toast.makeText(LogIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    db.connectUser(logInUsername);
                    Log.e("EHOOOOOOOO","EHOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    finish();
                } else {
                        Toast.makeText(LogIn.this, "Username or Password incorrect", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
