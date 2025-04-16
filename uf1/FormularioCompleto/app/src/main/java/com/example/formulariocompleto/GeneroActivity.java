package com.example.formulariocompleto;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GeneroActivity extends AppCompatActivity {

    protected TextView texto1;
    protected ImageButton imaboton1;
    protected ImageButton imaboton2;

    protected String paquete1="";
    protected String paquete2="";

    protected Bundle extras;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genero);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1=(TextView) findViewById(R.id.texto1_genero);
        imaboton1=(ImageButton) findViewById(R.id.imaboton1_genero);
        imaboton2=(ImageButton) findViewById(R.id.imaboton2_genero);

        extras=getIntent().getExtras();
        if (extras!=null) // no ha llegado algun paquete
        {
            paquete1=extras.getString("NOMBRE");
            paquete2=extras.getString("APELLIDOS");

            Toast.makeText(this, paquete1 +"- "+paquete2, Toast.LENGTH_SHORT).show();


        }
    }
}