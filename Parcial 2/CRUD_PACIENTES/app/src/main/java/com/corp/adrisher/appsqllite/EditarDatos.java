package com.corp.adrisher.appsqllite;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.corp.adrisher.appsqllite.db.DbPersonas;
import com.corp.adrisher.appsqllite.entidades.Datos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarDatos extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Boolean correcto = false;
    Datos datos;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuarda = findViewById(R.id.btnguardar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbPersonas dbPersonas = new DbPersonas(EditarDatos.this);
        datos = dbPersonas.verDatos(id);

        if (datos != null){
            txtNombre.setText(datos.getNombre());
            txtTelefono.setText(datos.getTelefono());
            txtCorreo.setText(datos.getCorreo_electronico());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")
                && !txtCorreo.getText().toString().equals("")){
                   correcto =  dbPersonas.editarPersona(id, txtNombre.getText().toString(), txtTelefono.getText().toString(),
                            txtCorreo.getText().toString());
                    if (correcto) {
                        Toast.makeText(EditarDatos.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else{

                        Toast.makeText(EditarDatos.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }else{

                    Toast.makeText(EditarDatos.this, "DEBE LLENAR LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerDatos.class);
        intent.putExtra("ID", id);
        startActivity(intent);

    }
}
