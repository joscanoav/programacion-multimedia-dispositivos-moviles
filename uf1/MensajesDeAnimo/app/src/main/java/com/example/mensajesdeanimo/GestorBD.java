package com.example.mensajesdeanimo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestorBD extends SQLiteOpenHelper {
    public GestorBD(@Nullable Context context) {
        super(context, "mensaje de animo", null, 1);
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


}
