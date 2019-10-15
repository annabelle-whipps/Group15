package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        ArrayList<String> diseases = new ArrayList<String>();

        diseases.add("Diabete");
        diseases.add("Asthma");

        ArrayList<String> allergies = new ArrayList<String>();

        allergies.add("Mosquito bites");
        allergies.add("lol");
        allergies.add("lol");
        allergies.add("lol");allergies.add("lol");
        allergies.add("lol");


        GridView grid = findViewById(R.id.gridDisease);
        GridView grid2 = findViewById(R.id.gridAllergy);

        //gets the logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo1_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final List<String> diseaseList = new ArrayList<String>(diseases);
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                (getApplicationContext(),android.R.layout.simple_list_item_1, diseaseList);
        grid.setAdapter(gridViewArrayAdapter);

        final List<String> allergyList = new ArrayList<String>(allergies);
        final ArrayAdapter<String> gridViewArrayAdapter2 = new ArrayAdapter<String>
                (getApplicationContext(),android.R.layout.simple_list_item_1, allergyList);
        grid2.setAdapter(gridViewArrayAdapter2);

    }
}
