package com.example.mensajesdeanimo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GestorBD extends SQLiteOpenHelper {
    public GestorBD(@Nullable Context context) {
        super(context, "mensajesdeanimo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE mensajes(id INTEGER PRIMARY KEY AUTOINCREMENT, mensaje TEXT)");
        db.execSQL("INSERT INTO mensajes VALUES(null,'mensaje 1')");
        db.execSQL("INSERT INTO mensajes VALUES(null,'mensaje 2')");
        db.execSQL("INSERT INTO mensajes VALUES(null,'mensaje 3')");
        db.execSQL("INSERT INTO mensajes VALUES(null,'mensaje 4')");
        db.execSQL("INSERT INTO mensajes VALUES(null,'mensaje 5')");
        db.execSQL("INSERT INTO mensajes VALUES(null,'mensaje 6')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getMensajeAleatorio() {
        int pos = 0;
        Random rand = new Random();
        String salida = "";
        ArrayList<String> mensajes = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM mensajes", null);

        if (cur != null) {
            cur.moveToFirst();
            while (!cur.isAfterLast()) {
                mensajes.add(cur.getString(1));
                cur.moveToNext();
            }
        }

        pos = rand.nextInt(mensajes.size());
        salida = mensajes.get(pos);
        return salida;
    }




}
