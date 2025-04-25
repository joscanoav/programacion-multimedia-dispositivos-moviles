package com.example.ejercicioevaluableuf1dam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class DataBaseSQL extends SQLiteOpenHelper {
    public DataBaseSQL(Context ctx) {
        super(ctx, "notitas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla notas con columnas id y nota
        db.execSQL("CREATE TABLE notas (id INTEGER PRIMARY KEY AUTOINCREMENT, nota TEXT NOT NULL)");
        // ejemplos iniciales ES
        db.execSQL("INSERT INTO notas VALUES (null, 'Bajar al perro ES')");
        db.execSQL("INSERT INTO notas VALUES (null, 'Ordenar los libros ES')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS notas");
        onCreate(db);
    }

    // Inserta una nota, devuelve true si se creó
    public boolean insertNota(String texto) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO notas VALUES (null, '" + texto.replace("'", "''") + "')");
        db.close();
        return existsNota(texto);
    }

    // Obtiene todas las notas como "id.- texto"
    public ArrayList<String> getAllNotas() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM notas", null);
        if (c.moveToFirst()) {
            do {
                list.add(c.getInt(0) + ".- " + c.getString(1));
            } while (c.moveToNext());
        }
        c.close(); db.close();
        return list;
    }

    // Obtiene [id, texto] de la nota cuyo texto coincide (primer match)
    public ArrayList<String> getNota(String texto) {
        ArrayList<String> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT id, nota FROM notas WHERE nota='" + texto.replace("'", "''") + "'", null);
        if (c.moveToFirst()) {
            data.add(String.valueOf(c.getInt(0)));
            data.add(c.getString(1));
        }
        c.close(); db.close();
        return data;
    }

    // Borra nota por texto, devuelve true si ya no existe
    public boolean deleteNota(String texto) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE nota='" + texto.replace("'", "''") + "'");
        db.close();
        return !existsNota(texto);
    }

    // Borra todas las notas
    public void deleteAllNotas() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id>=0");
        db.close();
    }

    // Comprueba existencia por texto
    private boolean existsNota(String texto) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT 1 FROM notas WHERE nota='" + texto.replace("'", "''") + "'", null);
        boolean ok = c.getCount() > 0;
        c.close(); db.close();
        return ok;
    }
}