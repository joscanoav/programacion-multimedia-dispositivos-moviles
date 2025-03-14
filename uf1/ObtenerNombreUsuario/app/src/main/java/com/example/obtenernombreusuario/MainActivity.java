package com.example.obtenernombreusuario;

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

public class MainActivity extends AppCompatActivity {

    protected TextView texto1;
    protected EditText caja1;
    protected Button boton1;

    protected String contenidoCaja1="";





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

        caja1 = (EditText) findViewById(R.id.caja1_main);
        texto1 = (TextView) findViewById(R.id.text1_main);
        boton1 = (Button) findViewById(R.id.boton1_main);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contenidoCaja1 = caja1.getText().toString();
                if (contenidoCaja1.equals("")){
                    Toast.makeText(MainActivity.this, "Debe llenar la caja de texto", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Bienvenido a mi App : " + contenidoCaja1, Toast.LENGTH_SHORT).show();
                }
            }
        });


        //caja1.setText("Hola Dani");
        //boton1.setText("Lanzar");


    }



    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Me han pausado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "He vuelto", Toast.LENGTH_SHORT).show();

    }
}