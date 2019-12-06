package com.example.medpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class NewReminderActivity extends AppCompatActivity {

    MedPalDatabase db;
    Integer medicineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent launcher = getIntent();
        if (launcher.hasExtra("MEDICINE_ID")) {
            medicineId = launcher.getIntExtra("MEDICINE_ID", -1);
            //The key argument here must match that used in the other activity
        }

        db = new MedPalDatabase(NewReminderActivity.this);

        Button btnCreateReminder = findViewById(R.id.btnCreateReminder);

        btnCreateReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicineDetailsIntent = new Intent(NewReminderActivity.this, MedicineDetails.class);
                medicineDetailsIntent.putExtra("MEDICINE_ID", medicineId);
                startActivity(medicineDetailsIntent);
            }
        });


    }
}
