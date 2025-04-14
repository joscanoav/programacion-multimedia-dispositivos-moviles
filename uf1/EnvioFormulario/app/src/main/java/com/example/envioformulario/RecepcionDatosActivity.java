package com.example.envioformulario;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecepcionDatosActivity extends AppCompatActivity {

    protected TextView texto1;
    protected TextView texto2;

    protected TextView texto3;

    protected TextView texto4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recepcion_datos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1=(TextView) findViewById(R.id.texto1_recepcion);
        texto2=(TextView) findViewById(R.id.texto2_recepcion);
        texto3=(TextView) findViewById(R.id.texto3_recepcion);
        texto4=(TextView) findViewById(R.id.texto4_recepcion);
    }
}