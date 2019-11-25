package com.example.medpal;

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

        db.insertPractitionerData("Dolittle","+44 1234 5678 89","9 rue du soleil levant Houdan","dolittle@gmail.uk");
        db.insertEmergencyContactData("Kate Williams","+44 9876 5432 100","6 rue des clos ribauds Bessancourt","mother@gmail.uk");

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
}
