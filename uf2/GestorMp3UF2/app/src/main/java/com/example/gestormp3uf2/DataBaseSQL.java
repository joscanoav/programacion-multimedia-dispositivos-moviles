package com.example.gestormp3uf2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "audio";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MEDIA = "media";

    public DataBaseSQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_MEDIA + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "titulo TEXT NOT NULL, "
                + "url TEXT NOT NULL"
                + ")");

        // Datos iniciales
        db.execSQL("INSERT INTO media (titulo, url) VALUES ('Energetic Rock Music', 'https://cdn.pixabay.com/audio/2025/04/29/audio_d27870aeed.mp3')");
        db.execSQL("INSERT INTO media (titulo, url) VALUES ('Energetic Rock', 'https://cdn.pixabay.com/audio/2025/04/28/audio_63ab78fd28.mp3')");
        db.execSQL("INSERT INTO media (titulo, url) VALUES ('Powerful Energy', 'https://cdn.pixabay.com/audio/2024/09/29/audio_1f10a186f0.mp3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIA);
        onCreate(db);
    }

    public void insertarAudio(String titulo, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titulo", titulo);
        cv.put("url", url);
        db.insert(TABLE_MEDIA, null, cv);
        db.close();
    }

    public ArrayList<String> getAllAudios() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, titulo FROM " + TABLE_MEDIA, null);
        if (c.moveToFirst()) {
            do {
                lista.add(c.getInt(0) + ".- " + c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return lista;
    }

    public ArrayList<String> getAudio(int id) {
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, titulo, url FROM " + TABLE_MEDIA + " WHERE id=?",
                new String[]{String.valueOf(id)});
        if (c.moveToFirst()) {
            datos.add(String.valueOf(c.getInt(0)));
            datos.add(c.getString(1));
            datos.add(c.getString(2));
        }
        c.close();
        db.close();
        return datos;
    }
}