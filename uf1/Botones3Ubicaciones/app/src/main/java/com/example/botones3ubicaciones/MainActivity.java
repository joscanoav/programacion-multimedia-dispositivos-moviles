package com.example.botones3ubicaciones;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    protected Button boton1;
    protected Button boton2;
    protected Button boton3;

    protected MediaPlayer sonidoRAW;

    protected MediaPlayer sonidoOnline;

    protected MediaPlayer sonidoSistema;

    protected String url = "https://cdn.pixabay.com/audio/2025/03/18/audio_c38aba1e54.mp3";

    protected String rutaCarpetaMP3 = "";

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            // Si no tenemos permisos
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show();
        } else {
            //Si ya tenemos permiso
            Toast.makeText(this, "Tenemos ya permisos del usuario", Toast.LENGTH_SHORT).show();
            boton3.setVisibility(View.VISIBLE);

        }
    }


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

        boton1 = (Button) findViewById(R.id.boton1_main);
        boton2 = (Button) findViewById(R.id.boton2_main);
        boton3 = (Button) findViewById(R.id.boton3_main);

        boton3.setVisibility(View.GONE);

        rutaCarpetaMP3 = Environment.getExternalStorageDirectory().getPath() + "/Download/cancion3.mp3";
        File f = new File(rutaCarpetaMP3);
        if (f.exists()) {
            checkPermission(Manifest.permission.READ_MEDIA_AUDIO, 200);
            // Si existe ruta url el fichero existe falta obtener permisos
        } else {
            Toast.makeText(MainActivity.this, "Fichero no existe", Toast.LENGTH_SHORT).show();
        }


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RAW
                sonidoRAW = MediaPlayer.create(MainActivity.this, R.raw.cancion3);
                sonidoRAW.start();
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Online
                try {
                    sonidoOnline = new MediaPlayer();
                    sonidoOnline.setDataSource(url);
                    sonidoOnline.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    sonidoOnline.prepareAsync();
                    sonidoOnline.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            Toast.makeText(MainActivity.this, "Reproducciendo audio ...", Toast.LENGTH_SHORT).show();
                            sonidoOnline.start();
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sistema

                try {
                    sonidoSistema=new MediaPlayer();
                    sonidoSistema.setDataSource(rutaCarpetaMP3);
                    sonidoSistema.prepare();
                    sonidoSistema.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            sonidoSistema.start();
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boton3.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "No ha dado permisos", Toast.LENGTH_SHORT).show();
            }
        }

    }
}