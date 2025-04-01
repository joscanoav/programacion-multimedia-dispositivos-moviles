package com.example.ejerciciopropuesto1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    protected TextView texto1;
    protected TextView texto2;
    protected EditText caja1;
    protected Button boton1;

    protected String contenidocaja1;

    protected ImageView ima1;

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

        texto1 = findViewById(R.id.texto1_main);
        texto2 = findViewById(R.id.texto2_main);
        boton1 = findViewById(R.id.boton1_main);
        caja1 = findViewById(R.id.caja1_main);
        ima1 = (ImageView) findViewById(R.id.ima1_main);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contenidocaja1 = caja1.getText().toString().trim(); // Eliminamos espacios en blanco

                if (contenidocaja1.equals("")) {
                    texto2.setText("Debe rellenar la caja de texto");
                    Toast.makeText(MainActivity.this, "Debe rellenar la caja de texto", Toast.LENGTH_SHORT).show();
                } else {
                    if (contenidocaja1.equalsIgnoreCase("blanco")) {
                        texto2.setText("Correcto");
                        ima1.setImageResource(R.drawable.correcto);
                        boton1.setEnabled(false);

                    } else {
                        texto2.setText("Error");
                        ima1.setImageResource(R.drawable.error);

                    }
                }
            }
        });
    }
}
//testing
