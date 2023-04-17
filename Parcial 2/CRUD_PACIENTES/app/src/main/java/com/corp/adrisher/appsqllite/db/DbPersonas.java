package com.corp.adrisher.appsqllite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.corp.adrisher.appsqllite.entidades.Datos;

import java.util.ArrayList;

public class DbPersonas extends DBHelper {

    Context context;

    public DbPersonas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPersona(String nombre, String telefono, String correo_electronico){
        long id = 0;
        try {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("telefono", telefono);
        values.put("correo_electronico", correo_electronico);

        id = db.insert("t_personas", null, values);
    }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Datos> mostrarDatos(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Datos> listaContactos = new ArrayList<>();
        Datos datos = null;
        Cursor cursorDatos = null;

        cursorDatos = db.rawQuery("SELECT * FROM " + "t_personas", null);

        if(cursorDatos.moveToFirst()){
            do {
                datos = new Datos();
                datos.setId(cursorDatos.getInt(0));
                datos.setNombre(cursorDatos.getString(1));
                datos.setTelefono(cursorDatos.getString(2));
                datos.setCorreo_electronico(cursorDatos.getString(3));
                listaContactos.add(datos);
            }while (cursorDatos.moveToNext());
        }
        cursorDatos.close();
        return listaContactos;
    }


    public Datos verDatos(int id){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Datos datos = null;
        Cursor cursorDatos;

        cursorDatos = db.rawQuery("SELECT * FROM " + "t_personas" + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorDatos.moveToFirst()){
            datos = new Datos();
            datos.setId(cursorDatos.getInt(0));
            datos.setNombre(cursorDatos.getString(1));
            datos.setTelefono(cursorDatos.getString(2));
            datos.setCorreo_electronico(cursorDatos.getString(3));
        }
        cursorDatos.close();
        return datos;
    }

    public boolean editarPersona(int id, String nombre, String telefono, String correo_electronico){
        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + " t_personas " + " SET nombre = '"+nombre+"', telefono = '"+telefono+"', correo_electronico = '" + correo_electronico + "' WHERE id = '"+ id +"' "  );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }


    public boolean eliminarPersona(int id){
        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + " t_personas " + " WHERE id = ' " + id + "'" );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }


}
