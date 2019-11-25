package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ImportantContacts extends AppCompatActivity {

    MedPalDatabase db;
    final String NOT_FILLED = "Not filled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_contacts);

        //gets the logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo1_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        fillingLabelsFromDatabase();

        addImplicitIntent();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fillingLabelsFromDatabase();
    }

    private void fillingLabelsFromDatabase() {
        db = new MedPalDatabase(ImportantContacts.this);

        TextView gpPhone = findViewById(R.id.gpPhone);
        TextView gpMailLabel = findViewById(R.id.gpMailLabel);
        TextView gpAddress = findViewById(R.id.gpAddress);
        TextView gpName = findViewById(R.id.gpName);
        TextView ecPhone = findViewById(R.id.ecPhone);
        TextView ecAddress = findViewById(R.id.ecAddress);
        TextView ecMailLabel = findViewById(R.id.ecMailLabel);
        TextView ecRelation = findViewById(R.id.ecRelation);
        TextView ecName = findViewById(R.id.ecName);

        Contact practitioner = db.retrievePractitioner();
        Contact ec = db.retrieveEmergencyContact();

        if(practitioner != null){
            gpPhone.setText(practitioner.getNumber());
            gpAddress.setText(practitioner.getAddress());
            gpMailLabel.setText(practitioner.getEmail());
            gpName.setText("Dr. "+practitioner.getName());
        } else {
            gpPhone.setText(NOT_FILLED);
            gpAddress.setText(NOT_FILLED);
            gpMailLabel.setText(NOT_FILLED);
            gpName.setText(NOT_FILLED);
        }

        if(ec != null){
            ecPhone.setText(ec.getNumber());
            ecAddress.setText(ec.getAddress());
            ecMailLabel.setText(ec.getEmail());
            ecName.setText(ec.getName());
            ecRelation.setText(ec.getRelation());
        } else {
            ecPhone.setText(NOT_FILLED);
            ecAddress.setText(NOT_FILLED);
            ecMailLabel.setText(NOT_FILLED);
            ecName.setText(NOT_FILLED);
            ecRelation.setText(NOT_FILLED);
        }
    }

    private void addImplicitIntent() {
        Button btnEdit = findViewById(R.id.btnEdit);
        final Button gpCall = findViewById(R.id.gpCall);
        Button gpMail = findViewById(R.id.gpMail);
        final Button gpAddress = findViewById(R.id.gpGPS);
        Button ecCall = findViewById(R.id.ecCall);
        Button ecMail = findViewById(R.id.ecMail);
        Button ecAddress = findViewById(R.id.ecGPS);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent importantContactsEditIntent = new Intent(ImportantContacts.this, ImportantContactsEdit.class);
                startActivity(importantContactsEditIntent);
            }
        });

        gpCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView gpPhone = findViewById(R.id.gpPhone);
                if(gpPhone.getText().toString() != NOT_FILLED){
                    dialPhoneNumber(gpPhone.getText().toString());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"There is no number filled !",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        ecCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ecPhone = findViewById(R.id.ecPhone);
                if(ecPhone.getText().toString() != NOT_FILLED){
                    dialPhoneNumber(ecPhone.getText().toString());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"There is no number filled !",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        ecMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ecMail = findViewById(R.id.ecMailLabel);
                if(ecMail.getText().toString() != NOT_FILLED){
                    launchEmail(ecMail.getText().toString());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"There is no email filled !",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        gpMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView gpMail = findViewById(R.id.gpMailLabel);
                if(gpMail.getText().toString() != NOT_FILLED){
                    launchEmail(gpMail.getText().toString());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"There is no email filled !",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        ecAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ecAddress = findViewById(R.id.ecAddress);
                if(ecAddress.getText().toString() != NOT_FILLED){
                    launchMap(ecAddress.getText().toString());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"There is no address filled !",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        gpAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView gpAddress = findViewById(R.id.gpAddress);
                if(gpAddress.getText().toString() != NOT_FILLED){
                    launchMap(gpAddress.getText().toString());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"There is no address filled !",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }

    /**
     * Method that launches the dial app of the phone and calls a specific number
     * Taken from https://stackoverflow.com/questions/34596644/android-intent-call-number
     * @param phoneNumber number to dial
     */
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Method that launches the mail app of the phone
     * @param email destination email
     */
    private void launchEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        // set the MIME time
        intent.setType("text/plain");

        // set the data to mailto: so that only email apps will use this
        intent.setData(Uri.parse("mailto:"));

        // set the recipients of the email
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});

        // check the intent can be resolved by the device
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * Method that launches the map app of the phone
     * @param address address to set the GPS to
     */
    private void launchMap(String address) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q="+address));
        // check the intent can be resolved by the device
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
