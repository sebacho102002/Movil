package com.corp.adrisher.appsqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.corp.adrisher.appsqllite.adaptadores.ListaDatosAdapter;
import com.corp.adrisher.appsqllite.db.DBHelper;
import com.corp.adrisher.appsqllite.db.DbPersonas;
import com.corp.adrisher.appsqllite.entidades.Datos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btncrearba;
    RecyclerView listaPersonas;
    ArrayList<Datos> listaArrayDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPersonas = findViewById(R.id.listaPersonas);

        listaPersonas.setLayoutManager(new LinearLayoutManager(this));
        DbPersonas dbPersonas =  new DbPersonas(MainActivity.this);

        listaArrayDatos = new ArrayList<>();

        ListaDatosAdapter adapter = new ListaDatosAdapter(dbPersonas.mostrarDatos());
        listaPersonas.setAdapter(adapter);
        btncrearba = findViewById(R.id.btncrearBase);

        /*
        btncrearba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dphelper = new DBHelper(MainActivity.this);
                SQLiteDatabase db = dphelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CORRECTAMENTE CREADA", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }
}