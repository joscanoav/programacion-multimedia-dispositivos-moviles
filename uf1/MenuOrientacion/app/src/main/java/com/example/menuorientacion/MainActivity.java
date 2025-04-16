package com.example.menuorientacion;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    protected ImageView ima1;
    protected TextView texto1;

    protected Intent pasarpantalla;

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


        ima1 = (ImageView) findViewById(R.id.ima1_main);
        texto1 = (TextView) findViewById(R.id.texto1_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id== R.id.menu_item_borrar)
        {
            Toast.makeText(this, "He pulsado en borrar", Toast.LENGTH_SHORT).show();
            texto1.setText("");
            return true;
        }
        else if (id==R.id.menu_item_reiniciar)
        {
            Toast.makeText(this, "He pulsado reiniciar", Toast.LENGTH_SHORT).show();
            pasarpantalla = new Intent(MainActivity.this, MainActivity.class);
            startActivity(pasarpantalla);

            return true;
        }

        else if (id==R.id.menu_item_segunda)
        {
            Toast.makeText(this, "He pulsado reiniciar", Toast.LENGTH_SHORT).show();
            pasarpantalla = new Intent(MainActivity.this, SegundaActividad.class);
            startActivity(pasarpantalla);

            return true;
        }

        else
        {
            return super.onOptionsItemSelected(item);
        }
        
        // Handle item selection.

    }

    }



