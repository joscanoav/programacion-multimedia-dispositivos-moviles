package com.example.listadoimagenjugadoresbaloncesto;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected TextView texto1;
    protected ImageView ima1;

    protected ListView lista1;

    protected ArrayList<String> nombreJugadores = new ArrayList<String>();

    protected ArrayAdapter<String> adaptador;

    protected String contenidoItem="";

    @SuppressLint("MissingInflatedId")
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

        texto1 = (TextView) findViewById(R.id.texto1_main);
        ima1 = (ImageView) findViewById(R.id.ima1_main);
        lista1 = (ListView) findViewById(R.id.lista1_main);

        nombreJugadores.add("Curry");
        nombreJugadores.add("Jordan");
        nombreJugadores.add("Lebron");
        nombreJugadores.add("Curry");
        nombreJugadores.add("Jordan");
        nombreJugadores.add("Lebron");
        nombreJugadores.add("Curry");
        nombreJugadores.add("Jordan");
        nombreJugadores.add("Lebron");

        adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,nombreJugadores);
        lista1.setAdapter(adaptador);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contenidoItem= parent.getItemAtPosition(position).toString();
                        if (contenidoItem.equalsIgnoreCase("Curry"))
                        {
                            ima1.setImageResource(R.drawable.curry);
                        }

                else if (contenidoItem.equalsIgnoreCase("Lebron"))
                {
                    ima1.setImageResource(R.drawable.lebron);
                }
                        else if (contenidoItem.equalsIgnoreCase("Jordan"))
                        {
                            ima1.setImageResource(R.drawable.jordan);
                        }






                Toast.makeText(MainActivity.this,"He pulsado un item " + contenidoItem , Toast.LENGTH_SHORT).show();
            }
        });




    }
}