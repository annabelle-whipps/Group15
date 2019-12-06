package com.example.medpal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MedicalRecordEdit extends AppCompatActivity {
    MedPalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record_edit);

        //gets the logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo1_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        fillingLabelsFromDatabase();

        Button btnUpdate = findViewById(R.id.btnUpdateMedRec);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMedicalRecord();
            }
        });
    }

    /**
     * Method to retrieve the contact details from the database and insert them into the EditTexts
     */
    private void fillingLabelsFromDatabase() {
        db = new MedPalDatabase(MedicalRecordEdit.this);

        TextView username = findViewById(R.id.personName);
        EditText dob = findViewById(R.id.personDob);
        EditText address = findViewById(R.id.personAddress);
        EditText postCode = findViewById(R.id.personPostCode);
        EditText city = findViewById(R.id.personCity);
        EditText phone = findViewById(R.id.personPhone);
        EditText diseases = findViewById(R.id.eVDiseasesList);
        EditText allergies = findViewById(R.id.allergiesList);

        Record medicalRecord = db.retrieveMedicalRecord();

        if(medicalRecord != null){
            dob.setText(medicalRecord.getDob());
            address.setText(medicalRecord.getAddress());
            postCode.setText(medicalRecord.getPostcode());
            city.setText(medicalRecord.getCity());
            phone.setText(medicalRecord.getPhone());
            diseases.setText(medicalRecord.getDisease());
            allergies.setText(medicalRecord.getAllergies());
        } else {
            dob.setText("");
            address.setText("");
            phone.setText("");
            diseases.setText("");
            allergies.setText("");
        }

        username.setText(db.getLoggedUser());
    }

    /**
     * Method to retrieve the inputs of the page and update the database with it
     */
    private void updateMedicalRecord() {
        ArrayList<EditText> inputs = new ArrayList<EditText>();
        EditText dob = findViewById(R.id.personDob);
        inputs.add(dob);
        EditText address = findViewById(R.id.personAddress);
        inputs.add(address);
        EditText postCode = findViewById(R.id.personPostCode);
        inputs.add(postCode);
        EditText city = findViewById(R.id.personCity);
        inputs.add(city);
        EditText phone = findViewById(R.id.personPhone);
        inputs.add(phone);
        EditText diseases = findViewById(R.id.eVDiseasesList);
        inputs.add(diseases);
        EditText allergies = findViewById(R.id.allergiesList);
        inputs.add(allergies);

        //In case the input is left blank
        for(int i = 0;i<inputs.size();i++){
            if(inputs.get(i).getText().toString().equals("")){
                inputs.get(i).setText("Not filled");
            }
        }

        db.insertMedicalRecordData(dob.getText().toString(),address.getText().toString(),postCode.getText().toString(),city.getText().toString(),phone.getText().toString(),diseases.getText().toString(),allergies.getText().toString());
        finish(); //Go back to the contact details page
    }


    /**
     * Method to ask confirmation from user if they want to leave the screen without saving changes
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
