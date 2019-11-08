package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

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

        btnMedicalCentres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicalCentresIntent = new Intent(HomePage.this, CentreInformation.class);
                startActivity(medicalCentresIntent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(HomePage.this, Setting.class);
                startActivity(settingsIntent);
            }
        });

        iBtnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.ibtnHelp){
                    launchPhone();
                }
            }
        });


    }

    private void launchPhone() {
        TelephonyManager tm =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); //Get the country via the SIM network
        downloadNumber(tm.getNetworkCountryIso());
    }

    private void downloadNumber(final String countryISO) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://raw.githubusercontent.com/EmergencyNumberAPI/data/master/data.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    EmergencyJsonParser parser = new EmergencyJsonParser(countryISO);
                    String number = parser.parseEmergencyJson(response);

                    /**
                     * Method to launch an Implicit Intent for a phone dialer
                     * Based on the code at https://developer.android.com/guide/components/intents-common#Phone
                     */
                    // create the Intent with the action to dial
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    // set the data to the phone number
                    intent.setData(Uri.parse("tel:"+number));

                    // check the intent can be resolved by the device
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Context context = getApplicationContext();
                    CharSequence text = error.getMessage();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
        queue.add(stringRequest);
    }

}

