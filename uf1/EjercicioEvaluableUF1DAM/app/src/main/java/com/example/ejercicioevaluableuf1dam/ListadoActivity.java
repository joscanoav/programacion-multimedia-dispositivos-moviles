package com.example.ejercicioevaluableuf1dam;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class ListadoActivity extends AppCompatActivity {

    protected ListView lvNotas;
    protected DataBaseSQL db;
    protected ArrayList<String> lista;
    protected ArrayAdapter<String> adaptador;
    protected String item;
    protected String[] partes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        // Inicializar vista y base de datos
        lvNotas = findViewById(R.id.lvNotas);
        db = new DataBaseSQL(this);
        cargar();

        // Click corto para ver nota
        lvNotas.setOnItemClickListener((parent, view, position, id) -> {
            item = (String) parent.getItemAtPosition(position);
            partes = item.split("\\.\\- ");
            Intent i = new Intent(this, VerNotaActivity.class);
            i.putExtra("texto", partes[1]);
            startActivity(i);
        });

        // Click largo para borrar una nota
        lvNotas.setOnItemLongClickListener((parent, view, position, id) -> {
            item = (String) parent.getItemAtPosition(position);
            partes = item.split("\\.\\- ");
            new AlertDialog.Builder(this)
                    .setMessage(getString(R.string.confirmar_borrar, partes[1]))
                    .setPositiveButton(getString(R.string.si), (dialog, which) -> {
                        if (db.deleteNota(partes[1])) cargar();
                    })
                    .setNegativeButton(getString(R.string.no), null)
                    .show();
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargar();
    }

    private void cargar() {
        lista = db.getAllNotas();
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lvNotas.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_crear) {
            startActivity(new Intent(this, CrearNotaActivity.class));
            return true;
        } else if (id == R.id.menu_espanol) {
            cambiarIdioma("es");
            return true;
        } else if (id == R.id.menu_catalan) {
            cambiarIdioma("ca");
            return true;
        } else if (id == R.id.menu_ingles) {
            cambiarIdioma("en");
            return true;
        } else if (id == R.id.menu_opciones) {
            startActivity(new Intent(this, BorrarNotasActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cambiarIdioma(String codigoIdioma) {
        // Guardar preferencia
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        prefs.edit().putString("idioma", codigoIdioma).apply();

        // Actualizar configuración
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        Locale locale = new Locale(codigoIdioma);
        Locale.setDefault(locale);
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());

        // Reiniciar toda la aplicación
        Intent refresh = new Intent(this, StartActivity.class);
        startActivity(refresh);
        finishAffinity();
    }

}
