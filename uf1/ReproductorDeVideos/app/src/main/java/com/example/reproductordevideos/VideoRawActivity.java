package com.example.reproductordevideos;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class VideoRawActivity extends AppCompatActivity {

    protected VideoView video1;
    protected Uri videoRaw;

    protected MediaController mc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_raw);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        video1= (VideoView) findViewById(R.id.video1_raw);


        videoRaw= Uri.parse("android.resource://com.example.reproductordevideos/raw/video1");

        mc= new MediaController(VideoRawActivity.this);
        video1.setVideoURI(videoRaw);
        video1.setMediaController(mc);
        mc.setAnchorView(video1);

        video1.start();









    }
}