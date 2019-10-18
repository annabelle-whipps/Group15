package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button btnMyPrescriptions = findViewById(R.id.btnMyPrescriptions);
        Button btnMedicalRecord = findViewById(R.id.btnMedicalRecord);
        Button btnImportantContacts = findViewById(R.id.btnImportantContacts);
        Button btnMedicalCentres = findViewById(R.id.btnMedicalCentres);
        Button btnSettings = findViewById(R.id.btnSettings);
        ImageButton iBtnHelp = findViewById(R.id.ibtnHelp);

        // add onClick listeners to the buttons

        btnMyPrescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myPrescriptionsIntent = new Intent(HomePage.this, MyPrescriptions.class);
                startActivity(myPrescriptionsIntent);
            }
        });

        btnMedicalRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicalRecordIntent = new Intent(HomePage.this, MedicalRecord.class);
                startActivity(medicalRecordIntent);
            }
        });

        btnImportantContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent importantContactsIntent = new Intent(HomePage.this, ImportantContacts.class);
                startActivity(importantContactsIntent);
            }
        });

       /* btnMedicalCentres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicalCentresIntent = new Intent(HomePage.this, MedicalCentres.class);
                startActivity(medicalCentresIntent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(HomePage.this, Settings.class);
                startActivity(settingsIntent);
            }
        });*/

        iBtnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.ibtnHelp){
                    launchPhone();
                }
            }
        });


    }

    /**
     * Method to launch an Implicit Intent for a phone dialer
     * Based on the code at https://developer.android.com/guide/components/intents-common#Phone
     */
    private void launchPhone() {
        // create the Intent with the action to dial
        Intent intent = new Intent(Intent.ACTION_DIAL);
        // set the data to the phone number
        intent.setData(Uri.parse("tel:+0000000000"));

        // check the intent can be resolved by the device
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

