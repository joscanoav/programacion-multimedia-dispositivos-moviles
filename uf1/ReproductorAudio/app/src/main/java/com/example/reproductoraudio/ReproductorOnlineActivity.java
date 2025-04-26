package com.example.reproductoraudio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReproductorOnlineActivity extends AppCompatActivity {

    protected TextView texto1;
    protected ImageButton imabotonPlay;
    protected ImageButton imabotonPause;

    protected ImageButton imabotonStop;

    protected MediaPlayer mp;
    protected float milisegundo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reproductor_online);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1=(TextView) findViewById(R.id.text1_online);
        imabotonPlay=(ImageButton) findViewById(R.id.imabotonPlay_online;
        imabotonPause=(ImageButton) findViewById(R.id.imabotonPause_online);
        imabotonStop=(ImageButton) findViewById(R.id.imabotonStop_online);

        imabotonPause.setVisibility(View.GONE);
        imabotonStop.setVisibility(View.GONE);


        imabotonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play


            }
        });

        imabotonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pause




            }
        });

        imabotonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //stop



            }
        });
    }
}