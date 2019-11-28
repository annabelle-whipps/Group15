package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    MedPalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkUserIsLoggedIn();

        login =(Button)findViewById(R.id.logInButton);
        register =(Button)findViewById(R.id.signUpButton);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginActivity = new Intent(MainActivity.this, LogIn.class);
                startActivity(goToLoginActivity);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterActivity = new Intent(MainActivity.this, Register.class);
                startActivity(goToRegisterActivity);
            }
        });
    }

    /**
     * Method to check that if the user is already logged in, he is sent to the home page of the app
     */
    private void checkUserIsLoggedIn() {
        db = new MedPalDatabase(MainActivity.this);
        if(db.getLoggedUser() != null){
            Intent goToHomeActivity = new Intent(MainActivity.this, HomePage.class);
            startActivity(goToHomeActivity);
            Toast toast = Toast.makeText(getApplicationContext(), "Welcome back "+db.getLoggedUser(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
