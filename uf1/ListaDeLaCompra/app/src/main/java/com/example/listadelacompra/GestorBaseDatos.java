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
        db.execSQL("INSERT INTO productos VALUES (null, 'producto ejemplo 1',30,5)");
        db.execSQL("INSERT INTO productos VALUES (null, 'producto ejemplo 2',15,8)");
        db.execSQL("INSERT INTO productos VALUES (null, 'producto ejemplo 3',10,6)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean actualizarProducto(int id, String nombre, float precio, int cantidad)
    {
        if (existeIdProducto(Integer.toString(id)))
        {
            SQLiteDatabase db= this.getWritableDatabase();
            db.execSQL("UPDATE productos SET nombre='"+nombre+"',precio="+precio+",cantidad="+cantidad+" WHERE id="+id);
        }
        return true;
    }

    public void borrarTodosLosProducto()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM productos WHERE id>=0");
    }


    public boolean borrarProducto(String id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM productos WHERE id="+id);
        if (existeIdProducto(id)){
            return false;
        }
        return true;
    }

    public boolean insertarProducto (String nombre, float precio, int cantidad)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!existeProducto(nombre)){
            db.execSQL("INSERT INTO productos VALUES (null, '"+nombre+"',"+precio+","+cantidad+" )");
            if (existeProducto(nombre))
            {
                return true;
            }
        }
        return false;
    }

    public Producto obtenerProducto(String id){
        Producto pro;
        if (existeIdProducto(id)) {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM productos WHERE id=" + id, null);

            if (cur != null) {
                cur.moveToFirst();
                pro= new Producto(cur.getInt(0),cur.getString(1),cur.getFloat(2),cur.getInt(3));
                return pro;
            }
        }
        return null;
    }
        public ArrayList<String> obtenerProducto()
        {
            ArrayList<String> productos = new ArrayList<String>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM productos", null);
            if (cur!=null)
            {
                cur.moveToFirst();
                while (!cur.isAfterLast())
                {
                    //codigo para obtener la informacion
                    productos.add(cur.getString(0)+",-" + cur.getString(1)+ "(" + cur.getString(2)+" euros)("+ cur.getString(3)+ " u)");
                    cur.moveToNext();
                }
            }



            return productos;

        }

    public boolean existeProducto(String nombre)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM productos WHERE nombre='"+nombre+"'", null);
        if (cur!=null)
        {
            cur.moveToLast();
            if (cur.getCount()>0) {
                return true;
            }
        }

        return false;

    }

    public boolean existeIdProducto(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM productos WHERE id="+id,null);
        if (cur!=null)
        {
            cur.moveToLast();
            if (cur.getCount()>0) {
                return true;
            }
        }

        return false;

    }

    }

