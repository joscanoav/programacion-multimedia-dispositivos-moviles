package com.example.listadelacompra;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActualizarProductoActivity extends AppCompatActivity {

    protected TextView texto1;
    protected EditText caja1;
    protected EditText caja2;
    protected EditText caja3;

    protected Button boton1;
    protected Button boton2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actualizar_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1=(TextView) findViewById(R.id.texto1_actu);
        caja1=(EditText) findViewById(R.id.caja1_actu);
        caja2=(EditText) findViewById(R.id.caja2_actu);
        caja3=(EditText) findViewById(R.id.caja3_actu);
        boton1=(Button) findViewById(R.id.boton1_actu);
        boton2=(Button) findViewById(R.id.boton2_actu);

    }
}