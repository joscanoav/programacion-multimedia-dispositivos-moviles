package com.example.listadelacompra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestorBaseDatos extends SQLiteOpenHelper {



    public GestorBaseDatos(@Nullable Context context) {
        super(context, "listacompra", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE productos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio FLOAT, cantidad INTEGER)");
        db.execSQL("INSERT INTO products VALUES (null, 'producto ejemplo 1',30,5)");
        db.execSQL("INSERT INTO products VALUES (null, 'producto ejemplo 2',15,8)");
        db.execSQL("INSERT INTO products VALUES (null, 'producto ejemplo 3',10,6)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
        public ArrayList<String> obtenerProducto()
        {
            ArrayList<String> productos = new ArrayList<String>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM productos", null);
            if (cur!=null)
            {
                cur.moveToFirst();
                while (cur.isAfterLast())
                {
                    //codigo para obtener la informacion
                    productos.add(cur.getString(0)+",-" + cur.getString(1)+ "(" + cur.getString(2)+" euros)("+ cur.getString(3)+ " u)");
                    cur.moveToNext();
                }
            }



            return productos;

        }

    }

