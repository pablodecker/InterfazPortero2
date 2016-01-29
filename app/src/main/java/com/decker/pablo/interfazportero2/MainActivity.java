package com.decker.pablo.interfazportero2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //cambio v1.3
    EquipoCAPE myEquipoCAPE;
    ListView lista;
    String[] stArreglo;
    private String TAG = "Pablito";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getApplicationContext();


        Log.d(TAG, "**" + myEquipoCAPE.getNombre());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lista = (ListView)findViewById(R.id.listViewEquipos);
        //Aca es cuando hace click en el item
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int idSeleccionado = Integer.parseInt(stArreglo[position].split(" ")[0]);
                    cargarDatosSeleccion(idSeleccionado);

//                    Intent intent = new Intent(MainActivity.this,DatosEquipo.class);
                    Intent intent = new Intent(MainActivity.this,EquipoParticular.class);
                    intent.putExtra("idSeleccionado",idSeleccionado);
                    startActivity(intent);
             }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , DatosEquipo.class );
                startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }
    public void agregar_equipo(MainActivity v)
    {
//        Intent i = new Intent(MainActivity.this , DatosEquipo.class );
        Intent i = new Intent(MainActivity.this , EquipoParticular.class );
        startActivity(i);
    }

    @Override
    protected void onResume() {
        //esto es despues del onCreate, entra tambien cuando se vuelve desde otra activity
        super.onResume();
        cargar_datos();
    }

    public void cargar_datos(){
        BaseHelper myBaseHelper = new BaseHelper(this,"DBEquipos",null,1);
        SQLiteDatabase db = myBaseHelper.getReadableDatabase();
        if (db != null){
            Cursor c =  db.rawQuery("SELECT * FROM Equipos",null);
            int cantidad = c.getCount();
            stArreglo = new String[cantidad];
            int i = 0;
            if (c.moveToFirst()){
                do{
//                    String stDato = c.getInt(0) + " " + c.getString(0) + c.getInt(1) + " " + c.getString(1);
                      String stDato = c.getInt(0) +
                            " - " + c.getString(1) +
                            " - " + c.getString(2) +
                            " - " + c.getString(3) +
                            " - " + c.getString(4) +
                            " - " + c.getString(5);

                    stArreglo[i++] = stDato;
            }while (c.moveToNext());
            }
            //Creo un adapter para despues setearselo a la lista
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stArreglo);
            lista.setAdapter(adapter);
        }
    }

    public void cargarDatosSeleccion(int id)
    {
        BaseHelper myBaseHelper = new BaseHelper(this,"DBEquipos",null,1);
        SQLiteDatabase db = myBaseHelper.getReadableDatabase();
        if (db != null) {
            Cursor c = db.rawQuery("SELECT * FROM Equipos WHERE Id=" + id, null);
            //TablaEquipos: Id, Nombre, NumTel, Sal1, Sal2, Sal3, TipoEquipo
            try {
                if (c.moveToFirst()) {
//                    etNombre.setText(c.getString(1));
//                    etNumTel.setText(c.getString(2));
//                    etSal1.setText(c.getString(3));
//                    etSal2.setText(c.getString(4));
//                    etSal3.setText(c.getString(5));
//                    spTipoEquipo.setSelection(c.getInt(6));

                    myEquipoCAPE.setNombre(c.getString(1));
                    myEquipoCAPE.setNumTel(c.getString(2));
                    myEquipoCAPE.setSal1(c.getString(3));
                    myEquipoCAPE.setSal2(c.getString(4));
                    myEquipoCAPE.setSal3(c.getString(5));
                    myEquipoCAPE.setTipoEquipo(c.getInt(6));
                }
            } finally {
                c.close();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_Agregar) {
            agregar_equipo(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
