package com.example.formulariocompleto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InformacionActivity extends AppCompatActivity {

    protected TextView texto1;
    protected TextView texto2;

    protected EditText caja1;
    protected EditText caja2;

    protected Button boton1;
    
    protected String contenidoCaja1="";
    protected String contenidoCaja2="";

    protected Intent pasarpantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1=(TextView) findViewById(R.id.texto1_info);
        texto2=(TextView) findViewById(R.id.texto2_info);
        caja1=(EditText) findViewById(R.id.caja1_info);
        caja2=(EditText) findViewById(R.id.caja2_info);
        boton1=(Button) findViewById(R.id.boton1_info);
        
        
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                contenidoCaja1= caja1.getText().toString();
                contenidoCaja2=caja2.getText().toString();
                
                if (!contenidoCaja1.equalsIgnoreCase("")&& !contenidoCaja2.equalsIgnoreCase(""))
                {
                    // envia la informacion
                    pasarpantalla= new Intent(InformacionActivity.this,GeneroActivity.class);
                    pasarpantalla.putExtra("NOMBRE",contenidoCaja1);
                    pasarpantalla.putExtra("APELLIDOS",contenidoCaja2);
                    startActivity(pasarpantalla);


                }
                else
                {
                    Toast.makeText(InformacionActivity.this, getString(R.string.String_toast_info), Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        
        
        
        
        
    }
}
