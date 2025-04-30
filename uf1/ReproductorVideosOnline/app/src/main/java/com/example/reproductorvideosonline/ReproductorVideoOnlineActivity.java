package com.example.reproductorvideosonline;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReproductorVideoOnlineActivity extends AppCompatActivity {

    protected VideoView video1;
    protected Button boton1;

    protected Intent pasarPantalla;

    protected String paquete1="";

    protected Bundle extras;

    protected MediaController mc;

    protected Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reproductor_video_online);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        video1= (VideoView) findViewById(R.id.video1_repro);
        boton1=(Button) findViewById(R.id.boton1_repro);






        extras=getIntent().getExtras();
        if (extras!=null)
        {
            paquete1= extras.getString("URL");

            uri= Uri.parse(paquete1);
            video1.setVideoURI(uri);
            mc=new MediaController(ReproductorVideoOnlineActivity.this);
            mc.setAnchorView(video1);
            video1.setMediaController(mc);
            video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    video1.start();
                }
            });


            Toast.makeText(this, "paquete recibido: "+ paquete1, Toast.LENGTH_SHORT).show();



            boton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pasarPantalla= new Intent(ReproductorVideoOnlineActivity.this, MainActivity.class);
                    startActivity(pasarPantalla);
                }
            });
        }
        else
        {
            Toast.makeText(this, "No se han recibido paquetes", Toast.LENGTH_SHORT).show();
        }

    }
}