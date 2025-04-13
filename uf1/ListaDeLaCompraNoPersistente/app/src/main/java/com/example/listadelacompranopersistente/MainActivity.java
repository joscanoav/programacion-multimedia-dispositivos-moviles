package com.example.listadelacompranopersistente;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected CheckBox check1;
    protected TextView texto1;
    protected EditText caja1;
    protected Button boton1;
    protected ListView lista1;

    protected ArrayList<String> listaCompra = new ArrayList<String>();
    protected ArrayAdapter<String> adaptador;

    protected String contenidoCaja1 = "";


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

        texto1 = (TextView) findViewById(R.id.text1_main);
        caja1 = (EditText) findViewById(R.id.caja1_main);
        boton1 = (Button) findViewById(R.id.boton1_main);
        lista1 = (ListView) findViewById(R.id.lista1_main);
        check1 = (CheckBox) findViewById(R.id.check1_main);

        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check1.isChecked())
                {
                    boton1.setEnabled(false);
                }
                else
                {
                boton1.setEnabled(true);
                }

            }
        });


        //listaCompra.add("Manzanas");

        adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listaCompra);
        lista1.setAdapter(adaptador);

        lista1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Desea eliminar el texto seleccionado?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                listaCompra.remove(position);
                                adaptador.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(MainActivity.this, "No se borrara el elemento seleccionado", Toast.LENGTH_SHORT).show();
                            }
                        });
                // Create the AlertDialog object and return it.
                builder.create();
                builder.show();


                return true;
            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1 = caja1.getText().toString();
                if (contenidoCaja1.equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Debe rellenar la caja de texto", Toast.LENGTH_SHORT).show();
                } else {
                    listaCompra.add(contenidoCaja1);
                    caja1.setText("");
                    adaptador.notifyDataSetChanged();

                }
            }

        });


    }
}