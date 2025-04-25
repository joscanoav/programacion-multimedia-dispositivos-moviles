package com.example.ejercicioevaluableuf1dam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BorrarNotasActivity extends AppCompatActivity {

    private DataBaseSQL db;
    private Button btnBorrarTodas, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_borrar_notas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DataBaseSQL(this);
        btnBorrarTodas = findViewById(R.id.btnBorrarTodas);
        btnVolver = findViewById(R.id.btnVolver);

        btnBorrarTodas.setOnClickListener(v -> {
            db.deleteAllNotas();
            Toast.makeText(this, getString(R.string.toast_borrado_todo), Toast.LENGTH_SHORT).show();
            finish(); // volver a la actividad anterior
        });

        btnVolver.setOnClickListener(v -> {
            finish(); // volver sin hacer nada
        });
    }
}
