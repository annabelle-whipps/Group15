package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import java.util.ArrayList;

public class MyPrescriptions extends AppCompatActivity {

    private ArrayList<String> myPrescriptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prescriptions);

        initMyPrescriptions();
    }

    private void initMyPrescriptions() {
        myPrescriptions.add("Medicine 1");
        myPrescriptions.add("Medicine 2");
        myPrescriptions.add("Medicine 3");
        myPrescriptions.add("Medicine 4");
        myPrescriptions.add("Medicine 5");
        myPrescriptions.add("Medicine 6");
        myPrescriptions.add("Medicine 7");
        myPrescriptions.add("Medicine 8");
        myPrescriptions.add("Medicine 9");
        myPrescriptions.add("Medicine 10");
        myPrescriptions.add("Medicine 11");
        myPrescriptions.add("Medicine 12");
        myPrescriptions.add("Medicine 13");
        myPrescriptions.add("Medicine 14");
        myPrescriptions.add("Medicine 15");
        myPrescriptions.add("Medicine 16");
        myPrescriptions.add("Medicine 17");
        myPrescriptions.add("Medicine 18");
        myPrescriptions.add("Medicine 19");
        myPrescriptions.add("Medicine 20");
        myPrescriptions.add("Medicine 21");
        myPrescriptions.add("Medicine 22");
        myPrescriptions.add("Medicine 23");
        myPrescriptions.add("Medicine 24");
        myPrescriptions.add("Medicine 25");

        initMyPrescriptionsRecyclerView();
    }

    private void initMyPrescriptionsRecyclerView() {

         RecyclerView recyclerView = findViewById(R.id.recyclerView);
         MyPrescriptionsRecyclerViewAdapter adapter = new MyPrescriptionsRecyclerViewAdapter(myPrescriptions, this);
         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
