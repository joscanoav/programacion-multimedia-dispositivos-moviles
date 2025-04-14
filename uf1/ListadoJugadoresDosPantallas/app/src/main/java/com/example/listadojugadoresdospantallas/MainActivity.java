package com.example.listadojugadoresdospantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    protected ListView lista1;

    protected ArrayList<String> listajugadores = new ArrayList<String>();
    protected ArrayAdapter<String> adaptador;

    protected String contenidoItem="";

    protected Intent pasarPantalla;



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

        lista1 = (ListView) findViewById(R.id.lista1_main);

        listajugadores.add("Curry");
        listajugadores.add("Jordan");
        listajugadores.add("Lebron");

        adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,listajugadores);
        lista1.setAdapter(adaptador);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contenidoItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Item: " + contenidoItem, Toast.LENGTH_SHORT).show();
                pasarPantalla = new Intent(MainActivity.this, SegundaActividad.class);
                pasarPantalla.putExtra("PAQUETE", contenidoItem);
                startActivity(pasarPantalla);
            }
        });


    }
}