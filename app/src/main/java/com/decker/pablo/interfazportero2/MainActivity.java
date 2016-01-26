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
    ListView lista;
    String[] stArreglo;
    private String TAG = "Pablito";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "** Entro al Main");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lista = (ListView)findViewById(R.id.listViewEquipos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int idSeleccionado = Integer.parseInt(stArreglo[position].split(" ")[0]);
                    Intent intent = new Intent(MainActivity.this,DatosEquipo.class);
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
