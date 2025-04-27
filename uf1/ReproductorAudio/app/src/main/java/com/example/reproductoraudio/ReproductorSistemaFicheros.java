package com.example.reproductoraudio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;

public class ReproductorSistemaFicheros extends AppCompatActivity {

    protected TextView texto1;
    protected TextView texto2;

    protected ImageButton imabotonPlay;
    protected ImageButton imabotonPause;

    protected ImageButton imabotonStop;

    protected MediaPlayer mp;
    protected float milisegundo=0;

    protected String rutaCarpetaAudio="";

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(ReproductorSistemaFicheros.this,permission)== PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(ReproductorSistemaFicheros.this,new String[]{permission},requestCode);
        }
        else {
            Toast.makeText(this, "Tenemos ya permisos del usuario", Toast.LENGTH_SHORT).show();
            imabotonPlay.setVisibility(View.VISIBLE);

        }
    }


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

        imabotonPlay.setVisibility(View.GONE);
        imabotonPause.setVisibility(View.GONE);
        imabotonStop.setVisibility(View.GONE);

        rutaCarpetaAudio= Environment.getExternalStorageDirectory().getPath()+"/Download/cancion2.mp3";
        File f = new File(rutaCarpetaAudio);
        if (!f.exists())
        {
            imabotonPlay.setVisibility(View.GONE);
            imabotonPause.setVisibility(View.GONE);
            imabotonStop.setVisibility(View.GONE);
            Toast.makeText(this, "No se puede reproducir el fichero mp3", Toast.LENGTH_SHORT).show();
            texto2.setText("No se puede reproducir el fichero mp3");
        }
        else
        {
            texto2.setText(rutaCarpetaAudio);
        }
        checkPermission(Manifest.permission.READ_MEDIA_AUDIO,100);

        imabotonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play

                if (milisegundo > 0) {
                    mp.start();
                } else {

                    try {

                        mp = new MediaPlayer();
                        mp.setDataSource(rutaCarpetaAudio);
                        mp.prepare();
                        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                            }
                        });


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    imabotonPause.setVisibility(View.VISIBLE);
                    imabotonStop.setVisibility(View.VISIBLE);

                }
            }

        });

        imabotonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pause

                milisegundo = mp.getCurrentPosition();
                mp.pause();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if( requestCode==100)
        {
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                imabotonPlay.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(this, "No ha dado permisos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
