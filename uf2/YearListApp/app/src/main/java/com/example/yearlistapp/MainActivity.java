package com.example.yearlistapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewYears;
    ArrayList<String> yearsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewYears = (ListView) findViewById(R.id.lista1);
        yearsList = new ArrayList<>();

        for (int year = 1900; year <= 2025; year++){
            yearsList.add(String.valueOf(year));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                yearsList
        );

        listViewYears.setAdapter(adapter);

        listViewYears.setOnItemClickListener((parent,view,position,id) -> {
            String selectedYear = yearsList.get(position);
            Toast.makeText(this, "Has nacido el " + selectedYear, Toast.LENGTH_SHORT).show();

        });
    }
}