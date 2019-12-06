package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MedicineDetails extends AppCompatActivity {
    MedPalDatabase db;
    public final String NOT_FILLED = "Not filled";
    private ArrayList<String> reminders = new ArrayList<>();
    Integer medicineId;
    String practitionerNumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        Intent launcher = getIntent();
        if (launcher.hasExtra("MEDICINE_ID")) {
            medicineId = launcher.getIntExtra("MEDICINE_ID", -1);
            //The key argument here must match that used in the other activity
        }

        readFromDatabase();

        addImplicitIntent();

        initReminders();

    }

    //To make sure the activity is updated after the user adds a new reminder
    @Override
    protected void onRestart() {
        super.onRestart();
        readFromDatabase();
    }

    private void initReminders() {
        reminders.add("8:00 AM");
        reminders.add("1:00 PM");
        reminders.add("3:00 AM");

        initRemindersRecyclerView();
    }

    private void initRemindersRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.medicineRecyclerView);
        RemindersRecyclerViewAdapter adapter = new RemindersRecyclerViewAdapter(reminders, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Method to retrieve the contact details from the database and insert them into the TextViews
     */
    private void readFromDatabase() {
        db = new MedPalDatabase(MedicineDetails.this);

        TextView tvMedicineName = findViewById(R.id.tvMedicineName);
        TextView tvDose = findViewById(R.id.tvDose);
        TextView tvSideEffects = findViewById(R.id.tvSideEffects);
        TextView tvPrescribedBy = findViewById(R.id.tvPrescribedBy);
        TextView tvEtc = findViewById(R.id.tvEtc);

        Medicine medicine = db.retrieveMedicineDetails(medicineId);
        practitionerNumer = db.retrievePractitioner().getNumber();

        if(medicine != null){
            tvMedicineName.setText(medicine.getName());
            tvDose.setText(medicine.getDose());
            tvSideEffects.setText(medicine.getSideEffects());
            tvPrescribedBy.setText(medicine.getPrescriedBy());
            tvEtc.setText(medicine.getExtraInfo());
        } else {
            tvMedicineName.setText(NOT_FILLED);
            tvDose.setText(NOT_FILLED);
            tvSideEffects.setText(NOT_FILLED);
            tvPrescribedBy.setText(NOT_FILLED);
            tvEtc.setText(NOT_FILLED);
        }
    }

    /**
     * This method will add implicit intents on every button, with a verification in case the detail is not filled.
     */
    private void addImplicitIntent() {
        Button btnOrderMore = findViewById(R.id.btnOrderMore);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnAddReminder = findViewById(R.id.btnAddReminder);


        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newReminderIntent = new Intent(MedicineDetails.this, NewReminderActivity.class);
                newReminderIntent.putExtra("MEDICINE_ID", medicineId);
                startActivity(newReminderIntent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(practitionerNumer != null){
                    dialPhoneNumber(practitionerNumer);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"There is no practitioner number provided!",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        btnOrderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(practitionerNumer != null){
                    dialPhoneNumber(practitionerNumer);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"There is no practitioner number provided!",Toast.LENGTH_SHORT);
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
}
