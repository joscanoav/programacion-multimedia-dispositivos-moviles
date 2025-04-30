package com.example.reproductordevideos;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import android.Manifest;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class VideoSistemaActivity extends AppCompatActivity {

    protected VideoView video1;
    protected Uri videoRaw;

    protected MediaController mc;
    protected String rutaVideo="";

    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(VideoSistemaActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(VideoSistemaActivity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(VideoSistemaActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
            reproducirVideoSistema();
        }
    }


    public void reproducirVideoSistema()
    {


        Toast.makeText(this, "Cargando el video...", Toast.LENGTH_SHORT).show();
        mc= new MediaController(VideoSistemaActivity.this);
        video1.setVideoPath(rutaVideo);
        video1.setMediaController(mc);
        mc.setAnchorView(video1);

        video1.start();





    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_sistema);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        video1= (VideoView) findViewById(R.id.video1_sistema);

        rutaVideo= Environment.getExternalStorageDirectory().getPath()+"/Download/video1.mp4";
        Toast.makeText(this, "ruta: "+ rutaVideo, Toast.LENGTH_SHORT).show();
        File f= new File(rutaVideo);
        if (f.exists())
        {
            //El fichero existe
            Toast.makeText(this, "El fichero MP4 existe", Toast.LENGTH_SHORT).show();
            checkPermission(Manifest.permission.READ_MEDIA_VIDEO, 300);
        }
        else
        {
            Toast.makeText(this, "No se puede reproducir el video porque el fichero no existe", Toast.LENGTH_SHORT).show();
        }



        /*videoRaw= Uri.parse(rutaVideo);

        Toast.makeText(this, "Cargando el video...", Toast.LENGTH_SHORT).show();
        mc= new MediaController(VideoSistemaActivity.this);
        video1.setVideoURI(videoRaw);
        video1.setMediaController(mc);
        mc.setAnchorView(video1);

        video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(VideoSistemaActivity.this, "Video cargado", Toast.LENGTH_SHORT).show();
                video1.start();
            }
        });*/


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if (requestCode==300)
        {
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                reproducirVideoSistema();
            }
            else {
                Toast.makeText(this, "No ha dado permisos para acceder al sistema de ficheros", Toast.LENGTH_SHORT).show();
            }
        }

    }




}