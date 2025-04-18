package com.example.formulariocompleto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GeneroActivity extends AppCompatActivity {

    protected TextView texto1;
    protected ImageButton imaboton1;
    protected ImageButton imaboton2;

    protected String paquete1 = "";
    protected String paquete2 = "";

    protected Bundle extras;

    protected Intent pasarPantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genero);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1 = (TextView) findViewById(R.id.texto1_genero);
        imaboton1 = (ImageButton) findViewById(R.id.imaboton1_genero);
        imaboton2 = (ImageButton) findViewById(R.id.imaboton2_genero);

        extras = getIntent().getExtras();
        if (extras != null) // no ha llegado algun paquete
        {
            paquete1 = extras.getString("NOMBRE");
            paquete2 = extras.getString("APELLIDOS");

            Toast.makeText(this, paquete1 + "- " + paquete2, Toast.LENGTH_SHORT).show();

            imaboton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pasarPantalla = new Intent(GeneroActivity.this, EdadActivity.class);
                    // Crear un paquete para hombre

                    pasarPantalla.putExtra("GENERO", "hombre");

                    // Reenviar tambien paquete 1 y paquete 2

                    pasarPantalla.putExtra("NOMBRE", paquete1);
                    pasarPantalla.putExtra("APELLIDOS", paquete2);

                    startActivity(pasarPantalla);

                }
            });

            imaboton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pasarPantalla = new Intent(GeneroActivity.this, EdadActivity.class);
                    // Crear un paquete para mujer

                    pasarPantalla.putExtra("GENERO", "mujer");

                    // Reenviar tambien paquete 1 y paquete 2

                    pasarPantalla.putExtra("NOMBRE", paquete1);
                    pasarPantalla.putExtra("APELLIDOS", paquete2);

                    startActivity(pasarPantalla);


                }
            });
        } else {
            Toast.makeText(this, getString(R.string.String_toast_genero), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_genero, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.

        int id = item.getItemId();
        if (id == R.id.item_genero_inicio) {

            pasarPantalla = new Intent(GeneroActivity.this, InformacionActivity.class);
            startActivity(pasarPantalla);
            return true;

        }
        else
        {
            return super.onOptionsItemSelected(item);
        }


    }

}

