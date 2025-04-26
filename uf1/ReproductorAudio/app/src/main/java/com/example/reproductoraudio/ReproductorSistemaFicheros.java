package com.example.reproductoraudio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class ReproductorSistemaFicheros extends AppCompatActivity {

    protected TextView texto1;
    protected TextView texto2;

    protected ImageButton imabotonPlay;
    protected ImageButton imabotonPause;

    protected ImageButton imabotonStop;

    protected MediaPlayer mp;
    protected float milisegundo=0;

    protected String rutaCarpetaAudio="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reproductor_sistema_ficheros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1=(TextView) findViewById(R.id.text1_sistemafichero);
        texto2=(TextView) findViewById(R.id.text2_sistemafichero);
        imabotonPlay=(ImageButton) findViewById(R.id.imabotonPlay_sistemafichero);
        imabotonPause=(ImageButton) findViewById(R.id.imabotonPause_sistemafichero);
        imabotonStop=(ImageButton) findViewById(R.id.imabotonStop_sistemafichero);

        imabotonPause.setVisibility(View.GONE);
        imabotonStop.setVisibility(View.GONE);

        rutaCarpetaAudio= Environment.getExternalStorageDirectory().getPath()+"/Download/cancion1.mp3";
        File f = new File(rutaCarpetaAudio);
        if (f.exists())
        {
            Toast.makeText(this, "El fichero mp3 Existe", Toast.LENGTH_SHORT).show();
        }
        
        else{
            Toast.makeText(this, "El fichero no existe", Toast.LENGTH_SHORT).show();
        }
        
        Toast.makeText(this, "La ruta del sistema de fichero es: " + rutaCarpetaAudio, Toast.LENGTH_SHORT).show();
        texto2.setText(rutaCarpetaAudio);


        imabotonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play


                                imabotonPause.setVisibility(View.VISIBLE);
                                imabotonStop.setVisibility(View.VISIBLE);

                }

        });

        imabotonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pause

                imabotonPause.setVisibility(View.GONE);

            }
        });

        imabotonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //stop

                imabotonPause.setVisibility(View.GONE);
                imabotonStop.setVisibility(View.GONE);


            }
        });
    }

}
