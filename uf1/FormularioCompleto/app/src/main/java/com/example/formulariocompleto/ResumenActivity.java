package com.example.formulariocompleto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResumenActivity extends AppCompatActivity {
    protected TextView texto1;
    protected TextView texto2;
    protected TextView texto3;
    protected TextView texto4;
    protected TextView texto5;

    protected ImageView ima1;

    protected String paquete1 = "";
    protected String paquete2 = "";
    protected String paquete3 = "";
    protected String paquete4 = "";

    protected Bundle extras;

    protected Intent pasarPantalla;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resumen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1 = (TextView) findViewById(R.id.texto1_resumen);
        texto2 = (TextView) findViewById(R.id.texto2_resumen);
        texto3 = (TextView) findViewById(R.id.texto3_resumen);
        texto4 = (TextView) findViewById(R.id.texto4_resumen);
        texto5 = (TextView) findViewById(R.id.texto5_resumen);
        ima1 = (ImageView) findViewById(R.id.ima1_resumen);

        extras = getIntent().getExtras();
        if (extras != null) {
            paquete1 = extras.getString("NOMBRE");
            paquete2 = extras.getString("APELLIDOS");
            paquete3 = extras.getString("GENERO");
            paquete4 = extras.getString("EDAD");

            texto3.setText(getString(R.string.String_texto3_resumen) + paquete1);
            texto4.setText(getString(R.string.String_texto4_resumen) + paquete2);
            texto5.setText(getString(R.string.String_texto5_resumen) + paquete4);

            if (paquete3.equalsIgnoreCase("mujer")) {
                ima1.setImageResource(R.drawable.woman);
            } else {
                ima1.setImageResource(R.drawable.man);
            }

            Toast.makeText(this, paquete1 + " - " + paquete2 + " - " + paquete3 + " - " + paquete4, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, getString(R.string.String_toast_genero), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_resumen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.

        int id = item.getItemId();
        if (id == R.id.item_resumen_inicio) {

            pasarPantalla = new Intent(ResumenActivity.this, InformacionActivity.class);
            startActivity(pasarPantalla);
            return true;

        }
        else
        {
            return super.onOptionsItemSelected(item);
        }


    }

}





