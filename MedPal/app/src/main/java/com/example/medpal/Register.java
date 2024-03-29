package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    //Assigning ID to EditText;
    EditText regUsername, regPhone, regPassword, conPassword;
    Button btnRegister;

    //Calling in MedPal database as db;
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

        //Links database to Register.java activity.
        db = new MedPalDatabase(Register.this);

        //Button - assigns button in class to button in xml
        btnRegister = (Button) findViewById(R.id.btnRegister);

        //Gets the text from the EditTexts when button is clicked.
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringUsername = regUsername.getText().toString();
                String stringPhone = regPhone.getText().toString();
                String stringPassword = regPassword.getText().toString();
                String stringConPassword = conPassword.getText().toString();
                registerUser(stringUsername, stringPhone, stringPassword, stringConPassword);
            }
        });
    }

    /**
     * Method to register a new user in the database, while checking that the username is not taken and the passwords
     * are matching
     * @param stringUsername provided username
     * @param stringPhone provided phone
     * @param stringPassword provided password
     * @param stringConPassword provided confirmation password
     */
    private void registerUser(String stringUsername, String stringPhone, String stringPassword, String stringConPassword) {
        if (stringUsername.equals("")||stringPhone.equals("")||stringPassword.equals("")||stringConPassword.equals("")) { //Checks if the user has filled the EditText boxes, if not they are notified of this.
            Toast.makeText(Register.this,"Please enter registration details", Toast.LENGTH_SHORT).show();
        } else {
            if (stringPassword.equals(stringConPassword)) {
                Boolean checkUsername = db.checkUsername(stringUsername);
                if (checkUsername == true) { //Checks if username is available.
                    Boolean insertUserData = db.insertUserData(stringUsername, stringPhone, stringPassword, "1");
                    if (insertUserData == true) { //If insertUserData is true data is inserted into database and user notified of successful registration.
                        Intent homePageIntent = new Intent(Register.this, HomePage.class);
                        startActivity(homePageIntent); //Sends the user to the Home Page after successful registration.
                        Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                } else {
                    Toast.makeText(Register.this, "Username already taken", Toast.LENGTH_SHORT).show();
                }
            } else { //Notifies user if the password does not match the confirm password
                Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
