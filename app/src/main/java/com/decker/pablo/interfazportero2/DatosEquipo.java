package com.decker.pablo.interfazportero2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DatosEquipo extends AppCompatActivity {

    EditText etNombre, etNumTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_equipo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etNombre = (EditText) findViewById(R.id.editTextNombreEquipo);
        etNumTel = (EditText) findViewById(R.id.editTextNumTelEquipo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void guardar_datos(View v){

        BaseHelper myBaseHelper = new BaseHelper(this,"DBInterfaz",null,1);
        SQLiteDatabase db = myBaseHelper.getWritableDatabase();
        if (db !=null){
            ContentValues registronuevo = new ContentValues();
            registronuevo.put("Nombre",etNombre.getText().toString());
            registronuevo.put("NumTel",etNumTel.getText().toString());
            if (db.insert("Equipos",null,registronuevo) > 0){
                Toast.makeText(this, "Registro Insertado Con Exito", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
