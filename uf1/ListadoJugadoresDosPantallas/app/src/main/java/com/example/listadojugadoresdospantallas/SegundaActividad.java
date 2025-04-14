package com.example.listadojugadoresdospantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaActividad extends AppCompatActivity {

    protected ImageView ima1;
    protected Button boton1;
    
    protected String paquete1;
    
    protected Bundle extras;

    protected Intent pasarPantalla;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda_actividad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ima1=(ImageView) findViewById(R.id.ima1_segunda);
        boton1=(Button) findViewById(R.id.boton1_segunda);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(SegundaActividad.this, MainActivity.class);
                startActivity(pasarPantalla);
            }
        });
        
        //recojo todos los paquetes
        
        extras= getIntent().getExtras();
        
        if (extras!=null)// he recogido al menos un paquete
        {
            paquete1= extras.getString("PAQUETE");
            if (paquete1.equalsIgnoreCase("Lebron")){
                ima1.setImageResource(R.drawable.lebron);
            }
            else if (paquete1.equalsIgnoreCase("Curry")){
                ima1.setImageResource(R.drawable.curry);
            }
            if (paquete1.equalsIgnoreCase("Jordan")){
                ima1.setImageResource(R.drawable.jordan);
            }
        }
        else{
            Toast.makeText(this, "Sin paquete", Toast.LENGTH_SHORT).show();
        }

    }
}