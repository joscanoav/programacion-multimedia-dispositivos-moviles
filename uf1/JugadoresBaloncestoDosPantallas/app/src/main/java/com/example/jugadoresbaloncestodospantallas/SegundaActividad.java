package com.example.jugadoresbaloncestodospantallas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaActividad extends AppCompatActivity {
    
    protected String paquete1="";
    
    protected Bundle extras;
    
    protected ImageView ima1;
    
    protected TextView text1;

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
        
        getOnBackPressedDispatcher().addCallback(SegundaActividad.this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(SegundaActividad.this, "Pulsado boton atras", Toast.LENGTH_SHORT).show();
            }
        });
        
        ima1 = (ImageView) findViewById(R.id.ima1_segunda);
        text1 = (TextView) findViewById(R.id.text1_segunda);
        
        extras= getIntent().getExtras(); //recibe todos los paquetes
        
        if (extras!=null) {
            //Recepcion de paquetes
            paquete1 = extras.getString("JUGADOR");
            Toast.makeText(this, "Recibido: " + paquete1, Toast.LENGTH_SHORT).show();

            if (paquete1.equalsIgnoreCase("jordan"))
            {
                ima1.setImageResource(R.drawable.jordan);
                text1.setText("Jugador: " + paquete1);

            } else if (paquete1.equalsIgnoreCase("curry"))
            {
                ima1.setImageResource(R.drawable.curry);
                text1.setText("Jugador: " + paquete1);

            } else if (paquete1.equalsIgnoreCase("lebron")){
                ima1.setImageResource(R.drawable.lebron);
                text1.setText("Jugador: " + paquete1);

            }

        }
        else 
        {
            Toast.makeText(this, "No he recibido ningun paquete", Toast.LENGTH_SHORT).show();
        }
        
    }
}