package com.example.formulariocompleto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class EdadActivity extends AppCompatActivity {

    protected TextView texto1;
    protected TextView texto2;

    protected ListView lista1;

    protected String paquete1="";
    protected String paquete2="";
    protected String paquete3="";

    protected Bundle extras;

    protected ArrayList<String> numeros= new ArrayList<String>();

    protected ArrayAdapter<String> adaptador;

    protected int i;

    protected String contenidoItem="";

    protected Intent pasarPantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1 = (TextView) findViewById(R.id.texto1_edad);
        texto2 = (TextView) findViewById(R.id.texto2_edad);
        lista1 = (ListView) findViewById(R.id.lista1_edad);

        extras = getIntent().getExtras();
        if (extras != null) {
            paquete1 = extras.getString("NOMBRE");
            paquete2 = extras.getString("APELLIDOS");
            paquete3 = extras.getString("GENERO");

            Toast.makeText(this, paquete1 + " -" + paquete2 + " -" + paquete3, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, getString(R.string.String_toast_genero), Toast.LENGTH_SHORT).show();
        }

        i = 0;
        for (i = 0; i <= 150; i++) {
            numeros.add("" + i);
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numeros);
        lista1.setAdapter(adaptador);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                contenidoItem = parent.getItemAtPosition(position).toString();
                pasarPantalla = new Intent(EdadActivity.this, ResumenActivity.class);


                pasarPantalla.putExtra("NOMBRE", paquete1);
                pasarPantalla.putExtra("APELLIDOS", paquete2);
                pasarPantalla.putExtra("GENERO", paquete3);
                pasarPantalla.putExtra("EDAD", contenidoItem);


                startActivity(pasarPantalla);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.

        int id = item.getItemId();
        if (id == R.id.item_edad_inicio) {

            pasarPantalla = new Intent(EdadActivity.this, InformacionActivity.class);
            startActivity(pasarPantalla);
            return true;

        }
        else
        {
            return super.onOptionsItemSelected(item);
        }


    }

}


