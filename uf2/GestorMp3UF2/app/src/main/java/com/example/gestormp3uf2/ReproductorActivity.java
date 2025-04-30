package com.example.gestormp3uf2;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;

public class ReproductorActivity extends AppCompatActivity {

    protected MediaPlayer mp;
    protected DataBaseSQL db;
    protected TextView tvTitulo;
    protected TextView tvUrl;
    protected Button btnPlay;
    protected Button btnPause;
    protected Button btnStop;
    protected Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        tvTitulo = findViewById(R.id.tvAudioTitle);
        tvUrl = findViewById(R.id.tvAudioUrl);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        btnBack = findViewById(R.id.btnBack);

        String item = getIntent().getStringExtra("AUDIO_ITEM");
        int id = Integer.parseInt(item.split("\\.- ")[0]);

        db = new DataBaseSQL(this);
        ArrayList<String> d = db.getAudio(id);
        tvTitulo.setText(d.get(1));
        tvUrl.setText(d.get(2));

        mp = new MediaPlayer();
        mp.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
        );
        try {
            mp.setDataSource(d.get(2));
            mp.prepareAsync();
        } catch (IOException e) {
            Toast.makeText(this, getString(R.string.error_reproducir), Toast.LENGTH_SHORT).show();
        }

        btnPlay.setOnClickListener(v -> { if (!mp.isPlaying()) mp.start(); });
        btnPause.setOnClickListener(v -> { if (mp.isPlaying()) mp.pause(); });
        btnStop.setOnClickListener(v -> {
            mp.stop(); mp.reset();
            try {
                mp.setDataSource(d.get(2));
                mp.prepareAsync();
            } catch (IOException e) {
                Toast.makeText(this, getString(R.string.error_reproducir), Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) { mp.release(); mp = null; }
    }
}