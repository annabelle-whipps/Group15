package com.example.medpal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ImportantContactsEdit extends AppCompatActivity {

    MedPalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_contacts_edit);

        //gets the logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo1_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        fillingLabelsFromDatabase();

        Button btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContactDetails();
            }
        });
    }

    /**
     * Method to retrieve the contact details from the database and insert them into the EditTexts
     */
    private void fillingLabelsFromDatabase() {
        db = new MedPalDatabase(ImportantContactsEdit.this);

        EditText gpPhone = findViewById(R.id.gpPhone);
        EditText gpMail = findViewById(R.id.gpMail);
        EditText gpAddress = findViewById(R.id.gpAddress);
        EditText gpName = findViewById(R.id.gpName);
        EditText ecPhone = findViewById(R.id.ecPhone);
        EditText ecAddress = findViewById(R.id.ecAddress);
        EditText ecMail = findViewById(R.id.ecMail);
        EditText ecRelation = findViewById(R.id.ecRelation);
        EditText ecName = findViewById(R.id.ecName);

        Contact practitioner = db.retrievePractitioner();
        Contact ec = db.retrieveEmergencyContact();

        if(practitioner != null){
            gpPhone.setText(practitioner.getNumber());
            gpAddress.setText(practitioner.getAddress());
            gpMail.setText(practitioner.getEmail());
            gpName.setText(practitioner.getName());
        } else {
            gpPhone.setText("");
            gpAddress.setText("");
            gpMail.setText("");
            gpName.setText("");
        }

        if(ec != null){
            ecPhone.setText(ec.getNumber());
            ecAddress.setText(ec.getAddress());
            ecMail.setText(ec.getEmail());
            ecName.setText(ec.getName());
            ecRelation.setText(ec.getRelation());
        } else {
            ecPhone.setText("");
            ecAddress.setText("");
            ecMail.setText("");
            ecName.setText("");
            ecRelation.setText("");
        }
    }

    /**
     * Method to retrieve the inputs of the page and update the database with it
     */
    private void updateContactDetails() {
        ArrayList<EditText> inputs = new ArrayList<EditText>();
        EditText gpPhone = findViewById(R.id.gpPhone);
        inputs.add(gpPhone);
        EditText gpMail = findViewById(R.id.gpMail);
        inputs.add(gpMail);
        EditText gpAddress = findViewById(R.id.gpAddress);
        inputs.add(gpAddress);
        EditText gpName = findViewById(R.id.gpName);
        inputs.add(gpName);
        EditText ecPhone = findViewById(R.id.ecPhone);
        inputs.add(ecPhone);
        EditText ecAddress = findViewById(R.id.ecAddress);
        inputs.add(ecAddress);
        EditText ecMail = findViewById(R.id.ecMail);
        inputs.add(ecMail);
        EditText ecRelation = findViewById(R.id.ecRelation);
        inputs.add(ecRelation);
        EditText ecName = findViewById(R.id.ecName);
        inputs.add(ecName);

        //In case the input is left blank
        for(int i = 0;i<inputs.size();i++){
            if(inputs.get(i).getText().toString().equals("")){
                inputs.get(i).setText("Not filled");
            }
        }

        db.insertPractitionerData(gpName.getText().toString(),gpPhone.getText().toString(),gpAddress.getText().toString(),gpMail.getText().toString());
        db.insertEmergencyContactData(ecName.getText().toString(),ecPhone.getText().toString(),ecAddress.getText().toString(),ecMail.getText().toString(),ecRelation.getText().toString());

        finish(); //Go back to the contact details page
    }


    /**
     * Method to ask confirmation from user if he wants to leave before confirming changes
     * Taken from https://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity/2258147
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Leaving")
                .setMessage("Are you sure you want to leave? Your changes won't be saved !")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
