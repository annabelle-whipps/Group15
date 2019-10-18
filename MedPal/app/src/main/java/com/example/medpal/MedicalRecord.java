package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MedicalRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        //gets the logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo1_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

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
