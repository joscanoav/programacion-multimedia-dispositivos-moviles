package com.example.conversormoneda;

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

    EditText editTextCantidad;
    TextView textViewResultado;
    Button btnEuroADolar, btnDolarAEuro;

    final double CONVERSION_EURO_DOLAR = 1.21;

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

        editTextCantidad = findViewById(R.id.editTextNumber4);
        textViewResultado = findViewById(R.id.textView4);
        btnEuroADolar = findViewById(R.id.button);   // € a $
        btnDolarAEuro = findViewById(R.id.button2);  // $ a €

        btnEuroADolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda(true);
            }
        });

        // Botón de $ a €
        btnDolarAEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda(false);
            }
        });
    }

    private void convertirMoneda(boolean euroADolar) {
        String input = editTextCantidad.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Debe introducir una cantidad a convertir", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double cantidad = Double.parseDouble(input);
            double resultado;

            if (euroADolar) {
                resultado = cantidad * CONVERSION_EURO_DOLAR;
            } else {
                resultado = cantidad / CONVERSION_EURO_DOLAR;
            }

            String resultadoTexto = String.format("%.2f", resultado);
            textViewResultado.setText(resultadoTexto);
            Toast.makeText(this, "Operación realizada correctamente", Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor erróneo, introduzca un valor numérico", Toast.LENGTH_SHORT).show();
        }
    }
}
