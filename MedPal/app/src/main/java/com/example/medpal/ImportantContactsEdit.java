package com.example.medpal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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
    }

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
