package com.example.reproductordevideos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    protected TextView texto1;
    protected Button boton1;
    protected Button boton2;
    protected Button boton3;

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

        texto1 = (TextView) findViewById(R.id.texto1_main);
        boton1 = (Button) findViewById(R.id.boton1_main);
        boton2= (Button) findViewById(R.id.boton2_main);
        boton3 = (Button) findViewById(R.id.boton3_main);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // raw
                pasarPantalla = new Intent(MainActivity.this, VideoRawActivity.class);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // online
                pasarPantalla = new Intent(MainActivity.this, VideoOnlineActivity.class);

            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sistema
                pasarPantalla = new Intent(MainActivity.this, VideoSistemaActivity.class);

            }
        });


    }
}