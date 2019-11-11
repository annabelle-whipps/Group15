package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

    /** This method will be executed when a user wants to call emergency services
     * it will use the network of the phone's SIM card to determine the location and find the correct number
     * Based on the code at : https://stackoverflow.com/questions/11293642/how-can-i-get-my-android-device-country-code-without-using-gps*/
    private void launchPhone() {
        TelephonyManager tm =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); //Get the country via the SIM network
        downloadNumber(tm.getNetworkCountryIso());
    }

    /**
    * Method that will request a JSON file on a website
    * Based on the code at https://developer.android.com/training/volley/simple
     * @param countryISO The country's ISO code, retrieved from the phone's SIM network, to know the correct country
     *                   to find the appropriate emergency number
     */
    private void downloadNumber(final String countryISO) {
        RequestQueue queue = Volley.newRequestQueue(this);
        /**This JSON was found from a web API that can be found here : http://emergencynumberapi.com/*/
        String url ="https://raw.githubusercontent.com/EmergencyNumberAPI/data/master/data.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    /** This class will parse correctly the data to find the correct emergency phone number*/
                    EmergencyJsonParser parser = new EmergencyJsonParser(countryISO);
                    String number = parser.parseEmergencyJson(response);

                    /**
                     * Method to launch an Implicit Intent for a phone dialer
                     * Based on the code at https://developer.android.com/guide/components/intents-common#Phone
                     */

                    // create the Intent with the action to dial
                    Intent intent = new Intent(Intent.ACTION_DIAL);

                    if(!number.equals("ERR")){
                        // set the data to the phone number
                        intent.setData(Uri.parse("tel:"+number));
                    } else { //In case of an error
                        Context context = getApplicationContext();
                        CharSequence text = "Could not retrieve your local emergency number";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }


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

