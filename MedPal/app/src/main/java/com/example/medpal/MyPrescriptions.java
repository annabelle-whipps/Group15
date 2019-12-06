package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;

import java.util.ArrayList;

public class MyPrescriptions extends AppCompatActivity {

    MedPalDatabase db;
    public final String NOT_FILLED = "Not filled";
    private ArrayList<Medicine> myPrescriptions = new ArrayList<>();
    private ArrayList<Medicine> test = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prescriptions);

        seedMyPrescriptions();

        readPrescriptionsFromDatabase();
        //addImplicitIntent();
    }

    /**
     * Add medicine prescriptions details to the database
     * This is hard coded as the user should not be able to add/remove/change their prescriptions, they can only change reminders
     */
    private void seedMyPrescriptions() {
        db = new MedPalDatabase(MyPrescriptions.this);

        db.insertMedicineData("Adderall", "5 mg, 1/2 times a day", "Difficult, burning, or painful urination", "Potentially addictive and can cause insomnia");
        db.insertMedicineData("Ativan", "2-3 mg, 2/3 times a day", "Sedation", "May be taken with or without food.");
        db.insertMedicineData("Cymbbalta", "10 mg, once a day", "relaxed and calm\n", "Should not be given to children under the age of 18");
        db.insertMedicineData("Viagra", "6 mg, once a day", "chills or fever", "May be used off-label ");
        db.insertMedicineData("Oxycodone", "4 mg injeciton", "coughing or spitting up blood", "Potentially addictive and can cause insomnia");
        db.insertMedicineData("Zoloft", "5 mg, 1/2 times a day", "relaxed and calm\n", "May be taken with or without food.");
        db.insertMedicineData("Lyrica", "1.2 mg, 1/2 times a day", "Difficult, burning, or painful urination", "May be used off-label ");
        db.insertMedicineData("Naproxen", "5 mg, 1/2 times a day", "Drowsiness", "Potentially addictive and can cause insomnia");
        db.insertMedicineData("Metformin", "5 mg, 1/2 times a day", "coughing or spitting up blood", "May be taken with or without food.");
        db.insertMedicineData("Gabapentin", "5 mg, 1/2 times a day", "chills or fever", "May be used off-label ");
        db.insertMedicineData("Oxycodone", "5 mg, 1/2 times a day", "hearing loss", "Potentially addictive and can cause insomnia");
        db.insertMedicineData("Lisinopril", "5 mg, 1/2 times a day", "Nausea, Drowsiness", "May be used off-label ");
        db.insertMedicineData("Ciprofloxacin", "5 mg, 1/2 times a day", "Anxiety", "Should not be given to children under the age of 18");
        db.insertMedicineData("Codeine", "5 mg a day", "Difficult, burning, or painful urination", "N/A");

    }

    /**
     * Retrieve the medicine prescriptions for the currently logged user from the database
     */
    private void readPrescriptionsFromDatabase() {
        /*myPrescriptions.add(new Medicine(1, "Adderall", "5 mg, 1/2 times a day", "Difficult, burning, or painful urination", "", "Potentially addictive and can cause insomnia"));
        myPrescriptions.add(new Medicine(2, "Ativan", "2-3 mg, 2/3 times a day", "Sedation", "", "May be taken with or without food."));
        myPrescriptions.add(new Medicine(3, "Cymbbalta", "10 mg, once a day", "relaxed and calm\n", "", "Should not be given to children under the age of 18"));
        myPrescriptions.add(new Medicine(4, "Viagra", "6 mg, once a day", "chills or fever", "", "May be used off-label "));
        myPrescriptions.add(new Medicine(5, "Oxycodone", "4 mg injeciton", "coughing or spitting up blood", "", "Potentially addictive and can cause insomnia"));
        myPrescriptions.add(new Medicine(6, "Zoloft", "5 mg, 1/2 times a day", "relaxed and calm\n", "", "May be taken with or without food."));
        myPrescriptions.add(new Medicine(7, "Lyrica", "1.2 mg, 1/2 times a day", "Difficult, burning, or painful urination", "", "May be used off-label "));
        myPrescriptions.add(new Medicine(8, "Naproxen", "5 mg, 1/2 times a day", "Drowsiness", "", "Potentially addictive and can cause insomnia"));
        myPrescriptions.add(new Medicine(9, "Metformin", "5 mg, 1/2 times a day", "coughing or spitting up blood", "", "May be taken with or without food."));
        myPrescriptions.add(new Medicine(10, "Gabapentin", "5 mg, 1/2 times a day", "chills or fever", "", "May be used off-label "));
        myPrescriptions.add(new Medicine(11, "Oxycodone", "5 mg, 1/2 times a day", "hearing loss", "", "Potentially addictive and can cause insomnia"));
        myPrescriptions.add(new Medicine(12, "Lisinopril", "5 mg, 1/2 times a day", "Nausea, Drowsiness", "", "May be used off-label "));
        myPrescriptions.add(new Medicine(13, "Ciprofloxacin", "5 mg, 1/2 times a day", "Anxiety", "", "Should not be given to children under the age of 18"));
        myPrescriptions.add(new Medicine(14, "Codeine", "5 mg a day", "Difficult, burning, or painful urination", "", "N/A"));
*/
        myPrescriptions = db.retrieveUserPrescriptions();

        initMyPrescriptionsRecyclerView();
    }

    private void initMyPrescriptionsRecyclerView() {

         RecyclerView recyclerView = findViewById(R.id.recyclerView);
         MyPrescriptionsRecyclerViewAdapter adapter = new MyPrescriptionsRecyclerViewAdapter(myPrescriptions, this);
         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
