package com.decker.pablo.interfazportero2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditEquipo extends AppCompatActivity {

    EditText etNombre, etNumTel, etSal1, etSal2, etSal3;
    Spinner spTipoEquipo;
    String[] listaEquipos;
    Bundle b;
    int iEditar = 0;
    EquipoCAPE myEquipoCAPE;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_equipo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getApplicationContext();
        etNombre = (EditText) findViewById(R.id.editTextNombreEquipo);
        etNumTel = (EditText) findViewById(R.id.editTextNumTelEquipo);
//        etTipoEquipo = (EditText) findViewById(R.id.editTextTipoEquipo);
        spTipoEquipo = (Spinner)findViewById(R.id.spTipoEquipo);
        etSal1 = (EditText) findViewById(R.id.editTextSal1);
        etSal2 = (EditText) findViewById(R.id.editTextSal2);
        etSal3 = (EditText) findViewById(R.id.editTextSal3);
        listaEquipos = getResources().getStringArray(R.array.lista_de_equipos);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, listaEquipos);
        spTipoEquipo = (Spinner)findViewById(R.id.spTipoEquipo);
        spTipoEquipo.setAdapter(adapter);


        b = this.getIntent().getExtras();
        if (b != null){
            iEditar = b.getInt("Editar");
            if (iEditar == 1){
                id = myEquipoCAPE.getIdDB();
                etNombre.setText(myEquipoCAPE.getNombre());
                etNumTel.setText(myEquipoCAPE.getNumTel());
                etSal1.setText(myEquipoCAPE.getSal1());
                etSal2.setText(myEquipoCAPE.getSal2());
                etSal3.setText(myEquipoCAPE.getSal3());
                String sAux = myEquipoCAPE.getTipoEquipo();
                int j = 0;
                for (String s : listaEquipos) {
                    int i = s.indexOf(sAux);
                    if (i >= 0) {
                        spTipoEquipo.setSelection(j);
                        break;
                    }
                    j++;
                }
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iEditar == 0)
                    insertar_datos(view);
                else
                    modificar_datos(view);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
//        para crear el boton hacia atras (Back)(tambien agregar en el manifest el parentActivityName ....
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }
    private void cargar_tipo_equipo(){
    }

    public void RecuperarDatos(int id)
    {
            BaseHelper myBaseHelper = new BaseHelper(this,"DBEquipos",null,1);
            SQLiteDatabase db = myBaseHelper.getReadableDatabase();
            if (db != null) {
                Cursor c = db.rawQuery("SELECT * FROM Equipos WHERE Id=" + id, null);
                //TablaEquipos: Id, Nombre, NumTel, Sal1, Sal2, Sal3, TipoEquipo
                try {
                    if (c.moveToFirst()) {
                        etNombre.setText(c.getString(1));
                        etNumTel.setText(c.getString(2));
                        etSal1.setText(c.getString(3));
                        etSal2.setText(c.getString(4));
                        etSal3.setText(c.getString(5));
                        String sAux = c.getString(6);
                        int j = 0;
                        for (String s : listaEquipos) {
                            int i = s.indexOf(sAux);
                            if (i >= 0) {
                                spTipoEquipo.setSelection(j);
                                break;
                            }
                            j++;
                        }

//                        spTipoEquipo.setSelection(c.getString(6));

                    }
                } finally {
                    c.close();
                }
            }
    }

    public void insertar_datos(View v){
        BaseHelper myBaseHelper = new BaseHelper(this,"DBEquipos",null,1);
        SQLiteDatabase db = myBaseHelper.getWritableDatabase();
        if (db !=null){
            ContentValues registronuevo = new ContentValues();
            registronuevo.put("Nombre",etNombre.getText().toString());
            registronuevo.put("NumTel",etNumTel.getText().toString());
            registronuevo.put("Sal1",etSal1.getText().toString());
            registronuevo.put("Sal2",etSal2.getText().toString());
            registronuevo.put("Sal3",etSal3.getText().toString());
            String sAux = spTipoEquipo.getSelectedItem().toString();
            registronuevo.put("TipoEquipo",sAux);
            if (db.insert("Equipos",null,registronuevo) > 0){
                Toast.makeText(this, "Datos Guardados Con Exito", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void modificar_datos(View v){
        BaseHelper myBaseHelper = new BaseHelper(this,"DBEquipos",null,1);
        SQLiteDatabase db = myBaseHelper.getWritableDatabase();
        if (db !=null){
            ContentValues registronuevo = new ContentValues();
            registronuevo.put("Nombre",etNombre.getText().toString());
            registronuevo.put("NumTel",etNumTel.getText().toString());
            registronuevo.put("Sal1",etSal1.getText().toString());
            registronuevo.put("Sal2",etSal2.getText().toString());
            registronuevo.put("Sal3",etSal3.getText().toString());
            registronuevo.put("TipoEquipo",spTipoEquipo.getSelectedItem().toString());
            if (db.update("Equipos", registronuevo, "Id=" + id, null) > 0){
                Toast.makeText(this, "Datos Guardados Con Exito", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
