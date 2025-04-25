package com.example.ejercicioevaluableuf1dam;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CrearNotaActivity extends AppCompatActivity {
    protected EditText etNota;
    protected Button btnCrear, btnVolver;
    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_crear_nota);
        etNota   = findViewById(R.id.etNota);
        btnCrear = findViewById(R.id.btnCrear);
        btnVolver= findViewById(R.id.btnVolver);
        db       = new DataBaseSQL(this);

        btnCrear.setOnClickListener(v -> {
            String texto = etNota.getText().toString();
            if (TextUtils.isEmpty(texto)) {
                toast("Nota obligatoria");
            } else if (db.insertNota(texto)) {
                toast("Nota creada correctamente");
                finish();
            }
        });
        btnVolver.setOnClickListener(v->finish());
    }
    private void toast(String m){android.widget.Toast.makeText(this,m,android.widget.Toast.LENGTH_SHORT).show();}
}