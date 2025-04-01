package com.example.jugadoresbaloncesto;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private TextView texto1;

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;


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

        texto1 = (TextView)findViewById(R.id.texto1_main);
        imageButton1 = (ImageButton) findViewById(R.id.imabutton1_main);
        imageButton2 = (ImageButton) findViewById(R.id.imabutton2_main);
        imageButton3 = (ImageButton)  findViewById(R.id.imabutton3_main);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                imageButton1.setImageResource(R.drawable.error);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                imageButton2.setImageResource(R.drawable.error);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                imageButton3.setImageResource(R.drawable.correcto);
            }
        });

    }
}