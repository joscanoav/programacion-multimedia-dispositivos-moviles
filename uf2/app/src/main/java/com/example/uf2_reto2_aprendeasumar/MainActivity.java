package com.example.uf2_reto2_aprendeasumar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    protected TextView tvOperacion;
    protected Button btnOp1;
    protected Button  btnOp2;
    protected Button  btnOp3;

    protected MediaPlayer mpAcierto;
    protected MediaPlayer mpError;

    protected int resultadoCorrecto;

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

        tvOperacion = findViewById(R.id.tvOperacion);
        btnOp1      = findViewById(R.id.btnOp1);
        btnOp2      = findViewById(R.id.btnOp2);
        btnOp3      = findViewById(R.id.btnOp3);

        mpAcierto = MediaPlayer.create(this, R.raw.correcto);
        mpError   = MediaPlayer.create(this, R.raw.error);

        generarNuevaOperacion();
        configurarListeners();
    }

    protected void generarNuevaOperacion() {
        Random rnd = new Random();
        int a = rnd.nextInt(9) + 1;  // 1..9
        int b = rnd.nextInt(9) + 1;  // 1..9
        resultadoCorrecto = a + b;
        tvOperacion.setText(a + " + " + b + " = ?");

        ArrayList<Integer> opciones = new ArrayList<>();
        opciones.add(resultadoCorrecto);
        while (opciones.size() < 3) {
            int falsa = rnd.nextInt(17) + 2; // 2..18
            if (falsa != resultadoCorrecto && !opciones.contains(falsa)) {
                opciones.add(falsa);
            }
        }
        Collections.shuffle(opciones);

        btnOp1.setText(String.valueOf(opciones.get(0)));
        btnOp2.setText(String.valueOf(opciones.get(1)));
        btnOp3.setText(String.valueOf(opciones.get(2)));
    }

    protected void configurarListeners() {
        View.OnClickListener escuchaBoton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                int elegido = Integer.parseInt(btn.getText().toString());
                if (elegido == resultadoCorrecto) {
                    // acierto
                    mpAcierto.start();
                    // al terminar, reiniciar pregunta
                    mpAcierto.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            recreate();
                        }
                    });
                } else {
                    // error
                    mpError.start();
                }
            }
        };

        btnOp1.setOnClickListener(escuchaBoton);
        btnOp2.setOnClickListener(escuchaBoton);
        btnOp3.setOnClickListener(escuchaBoton);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpAcierto != null) mpAcierto.release();
        if (mpError   != null) mpError.release();
    }
}