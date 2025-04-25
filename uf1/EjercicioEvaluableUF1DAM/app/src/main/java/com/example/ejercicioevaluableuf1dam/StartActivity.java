package com.example.ejercicioevaluableuf1dam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import java.util.Locale;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Configurar idioma primero
        cargarIdiomaGuardado();

        // 2. Configurar interfaz
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);

        // 3. Ajustar bordes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 4. Retardo para cambiar de actividad
        new Handler().postDelayed(() -> {
            startActivity(new Intent(StartActivity.this, ListadoActivity.class));
            finish();
        }, 2000);
    }

    private void cargarIdiomaGuardado() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String idioma = prefs.getString("idioma", "es"); // Default: español
        configurarIdioma(idioma);
    }

    private void configurarIdioma(String codigoIdioma) {
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        Locale locale = new Locale(codigoIdioma);
        Locale.setDefault(locale);
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}