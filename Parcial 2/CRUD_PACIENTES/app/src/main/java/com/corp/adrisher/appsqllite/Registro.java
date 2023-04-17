package com.corp.adrisher.appsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.corp.adrisher.appsqllite.db.DbPersonas;

public class Registro extends AppCompatActivity {

    EditText txtNombre, txtTelf, txtCorreo;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelf = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuardar = findViewById(R.id.btnguardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbPersonas dbPersonas = new DbPersonas(Registro.this);
               long id = dbPersonas.insertarPersona(txtNombre.getText().toString(), txtTelf.getText().toString(), txtCorreo.getText().toString());

               if(id > 0){
                   Toast.makeText(Registro.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                   limpiar();
               }else{

                   Toast.makeText(Registro.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    private void limpiar(){
        txtNombre.setText("");
        txtTelf.setText("");
        txtCorreo.setText("");

    }
}