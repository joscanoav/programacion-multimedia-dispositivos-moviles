package com.example.envioformulario;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    protected TextView texto1;

    protected EditText caja1;
    protected EditText caja2;
    protected EditText caja3;

    protected Button boton1;

    protected String contenidoCaja1="";
    protected String contenidoCaja2="";
    protected String contenidoCaja3="";





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

        texto1=(TextView) findViewById(R.id.texto1_main);
        caja1=(EditText) findViewById(R.id.caja1_main);
        caja2=(EditText) findViewById(R.id.caja2_main);
        caja3=(EditText) findViewById(R.id.caja3_main);
        boton1=(Button) findViewById(R.id.boton1_main);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1 = caja1.getText().toString();
                contenidoCaja2 = caja2.getText().toString();
                contenidoCaja3 = caja3.getText().toString();
            }
        });






    }
}