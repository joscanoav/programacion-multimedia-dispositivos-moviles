package com.example.jugadoresbaloncestodospantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    protected TextView texto1;
    protected EditText caja1;
    protected ImageButton imaBoton1;

    protected Intent pasarPantalla;

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

        caja1= (EditText) findViewById(R.id.caja1_main);
        imaBoton1=(ImageButton) findViewById(R.id.boton1_main);
        texto1 = (TextView) findViewById(R.id.texto1_main);

        imaBoton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "voy a la siguiente actividad", Toast.LENGTH_SHORT).show();

                pasarPantalla= new Intent(MainActivity.this, SegundaActividad.class);
                finish();
                startActivity(pasarPantalla);

            }
        });
    }
}