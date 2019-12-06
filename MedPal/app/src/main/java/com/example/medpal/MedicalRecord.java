package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedicalRecord extends AppCompatActivity {

    MedPalDatabase db;
    public final String NOT_FILLED = "Not filled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        //gets the logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo1_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        fillingLabelsFromDatabase();
        addImplicitIntent();
    }

    //Making sure TextViews are updated after the user edits the details
    @Override
    protected void onRestart() {
        super.onRestart();
        fillingLabelsFromDatabase();
    }

    /**
     * Method to retrieve the contact details from the database and insert them into the TextViews
     */
    private void fillingLabelsFromDatabase() {
        db = new MedPalDatabase(MedicalRecord.this);

        TextView username = findViewById(R.id.personName);
        TextView dob = findViewById(R.id.dateOfBirth);
        TextView address = findViewById(R.id.personAddress);
        TextView postCode = findViewById(R.id.personPostCode);
        TextView city = findViewById(R.id.personCity);
        TextView phone = findViewById(R.id.personPhone);
        TextView diseases = findViewById(R.id.diseasesList);
        TextView allergies = findViewById(R.id.allergiesList);

        Record medicalRecord = db.retrieveMedicalRecord();

        if(medicalRecord != null){
            dob.setText(medicalRecord.getDob());
            address.setText(medicalRecord.getAddress());
            postCode.setText(medicalRecord.getAddress());
            city.setText(medicalRecord.getAddress());
            phone.setText(medicalRecord.getPhone());
            diseases.setText(medicalRecord.getDisease());
            allergies.setText(medicalRecord.getAllergies());
        } else {
            dob.setText(NOT_FILLED);
            address.setText(NOT_FILLED);
            postCode.setText(NOT_FILLED);
            city.setText(NOT_FILLED);
            phone.setText(NOT_FILLED);
            diseases.setText(NOT_FILLED);
            allergies.setText(NOT_FILLED);
        }

        username.setText(db.getLoggedUser());
    }

    /**
     * This method will add implicit intent on the edit button
     */
    private void addImplicitIntent() {
        Button btnEdit = findViewById(R.id.update);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicalRecordEditIntent = new Intent(MedicalRecord.this, MedicalRecordEdit.class);
                startActivity(medicalRecordEditIntent);
            }
        });
    }
}
