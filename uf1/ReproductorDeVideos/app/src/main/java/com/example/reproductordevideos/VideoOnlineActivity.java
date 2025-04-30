package com.example.reproductordevideos;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoOnlineActivity extends AppCompatActivity {


    protected VideoView video1;
    protected Uri videoRaw;

    protected MediaController mc;
    protected String urlVideo="https://sample-videos.com/video321/mp4/720/big_buck_bunny_720p_1mb.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_online);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        video1= (VideoView) findViewById(R.id.video1_online);


        videoRaw= Uri.parse(urlVideo);

        Toast.makeText(this, "Cargando el video...", Toast.LENGTH_SHORT).show();
        mc= new MediaController(VideoOnlineActivity.this);
        video1.setVideoURI(videoRaw);
        video1.setMediaController(mc);
        mc.setAnchorView(video1);

        video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(VideoOnlineActivity.this, "Video cargado", Toast.LENGTH_SHORT).show();
                video1.start();
            }
        });



    }
}