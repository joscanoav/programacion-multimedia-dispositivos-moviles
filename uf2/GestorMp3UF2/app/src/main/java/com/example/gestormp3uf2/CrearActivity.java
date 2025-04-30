package com.example.gestormp3uf2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearActivity extends AppCompatActivity {

    protected DataBaseSQL db;
    protected EditText etTitulo;
    protected EditText etUrl;
    protected Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        db = new DataBaseSQL(this);
        etTitulo = findViewById(R.id.etTitulo);
        etUrl = findViewById(R.id.etUrl);
        btnAdd = findViewById(R.id.btnAddAudio);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = etTitulo.getText().toString().trim();
                String u = etUrl.getText().toString().trim();
                if (t.isEmpty() || u.isEmpty()) {
                    Toast.makeText(CrearActivity.this,
                            getString(R.string.error_campos),
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.insertarAudio(t, u);
                    Toast.makeText(CrearActivity.this,
                            getString(R.string.audio_guardado),
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CrearActivity.this, StartActivity.class));
                    finish();
                }
            }
        });
    }
}