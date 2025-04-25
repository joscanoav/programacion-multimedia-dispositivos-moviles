package com.example.ejercicioevaluableuf1dam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class VerNotaActivity extends AppCompatActivity {

    private DataBaseSQL db;
    private TextView tvId, tvContenido;
    private Button btnBorrar, btnVolver;
    private String texto;
    private ArrayList<String> notaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvId = findViewById(R.id.tvId);
        tvContenido = findViewById(R.id.tvContenido);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnVolver = findViewById(R.id.btnVolver);

        db = new DataBaseSQL(this);

        // Recogemos el texto enviado desde ListadoActivity
        texto = getIntent().getStringExtra("texto");
        notaInfo = db.getNota(texto);

        // Mostramos ID y contenido de la nota
        if (notaInfo.size() >= 2) {
            tvId.setText("ID: " + notaInfo.get(0));
            tvContenido.setText(notaInfo.get(1));
        }

        btnBorrar.setOnClickListener(v -> {
            db.deleteNota(texto);
            Toast.makeText(this, getString(R.string.toast_nota_borrada), Toast.LENGTH_SHORT).show();
            finish(); // Volver a ListadoActivity
        });

        btnVolver.setOnClickListener(v -> {
            finish(); // Volver sin borrar
        });
    }
}
