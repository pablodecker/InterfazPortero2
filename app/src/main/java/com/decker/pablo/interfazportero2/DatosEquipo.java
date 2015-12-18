package com.decker.pablo.interfazportero2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DatosEquipo extends AppCompatActivity {

    EditText etNombre, etNumTel, etTipoEquipo, etSal1, etSal2, etSal3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_equipo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etNombre = (EditText) findViewById(R.id.editTextNombreEquipo);
        etNumTel = (EditText) findViewById(R.id.editTextNumTelEquipo);
        etTipoEquipo = (EditText) findViewById(R.id.editTextTipoEquipo);
        etSal1 = (EditText) findViewById(R.id.editTextSal1);
        etSal2 = (EditText) findViewById(R.id.editTextSal2);
        etSal3 = (EditText) findViewById(R.id.editTextSal3);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar_datos(view);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
//        para crear el boton hacia atras(tambien agregar en el manifest el parentActivityName ....
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void guardar_datos(View v){

        BaseHelper myBaseHelper = new BaseHelper(this,"DBInterfaz",null,1);
        SQLiteDatabase db = myBaseHelper.getWritableDatabase();
        if (db !=null){
            ContentValues registronuevo = new ContentValues();
            registronuevo.put("Nombre",etNombre.getText().toString());
            registronuevo.put("NumTel",etNumTel.getText().toString());
            registronuevo.put("TipoEquipo",etTipoEquipo.getText().toString());
            registronuevo.put("Sal1",etSal1.getText().toString());
            registronuevo.put("Sal2",etSal2.getText().toString());
            registronuevo.put("Sal3",etSal3.getText().toString());
            if (db.insert("Equipos",null,registronuevo) > 0){
                Toast.makeText(this, "Datos Guardados Con Exito", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
