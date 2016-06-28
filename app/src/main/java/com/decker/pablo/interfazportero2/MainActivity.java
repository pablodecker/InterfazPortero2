package com.decker.pablo.interfazportero2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //cambio v1.3
    EquipoCAPE myEquipoCAPE;
    ListView lista;
    ListViewAdapter lvAdapter;
    String[] saListaEquipos;
    int[] iaIndiceDB;
    int idSeleccionado;
    protected Object mActionMode;
    private String TAG = "Pablito";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getApplicationContext();


        Log.d(TAG, "**" + myEquipoCAPE.getNombre());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Equipos CAPE");
        setSupportActionBar(toolbar);
        lista = (ListView)findViewById(R.id.listViewEquipos);
        //Aca es cuando hace click en el item
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                idSeleccionado = Integer.parseInt(saArreglo[position].split(" ")[0]);
                 idSeleccionado = iaIndiceDB[position];
                cargarDatosSeleccion(idSeleccionado);
//                    Intent intent = new Intent(MainActivity.this,EditEquipo.class);
                Intent intent = new Intent(MainActivity.this,EquipoParticular.class);
                intent.putExtra("idSeleccionado",idSeleccionado);
                startActivity(intent);
             }
        });
        //Aca es cuando hace un click y lo deja apretado sobre un item
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                idSeleccionado = Integer.parseInt(saArreglo[position].split(" ")[0]);
                idSeleccionado = iaIndiceDB[position];
                cargarDatosSeleccion(idSeleccionado);
                //si ya estoy en el menu retornar falso
                if (mActionMode != null)
                    return false;
//                setTheme(R.style.Theme_AppCompat_Light_DarkActionBar);
                mActionMode = MainActivity.this.startSupportActionMode(amc);
                view.setSelected(true);
                //para colorear el item seleccionado
                view.setBackgroundColor(Color.parseColor("#222222"));
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , EditEquipo.class );
                i.putExtra("Editar",0);
                startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    public void agregar_equipo(MainActivity v){
        Intent i = new Intent(MainActivity.this , EquipoParticular.class );
        startActivity(i);
    }

    @Override
    protected void onResume() {
        //esto es despues del onCreate, entra tambien cuando se vuelve desde otra activity
        super.onResume();
        cargar_datos_db_listview();
        EquipoCAPE.activityResumed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        EquipoCAPE.activityPaused();
    }

    public void cargar_datos_db_listview(){
        BaseHelper myBaseHelper = new BaseHelper(this,"DBEquipos",null,1);
        SQLiteDatabase db = myBaseHelper.getReadableDatabase();
        if (db != null){
            Cursor c =  db.rawQuery("SELECT * FROM Equipos",null);
            int cantidad = c.getCount();
            String[] saArreglo = new String[cantidad];
            String[] saArregloSoloNombre = new String[cantidad];
            //Obtengo la lista de equipos de un array
            saListaEquipos = getResources().getStringArray(R.array.lista_de_equipos);
            int[] iImagenes = new int[cantidad];
            //aca voy a guardar los indices que tiene la DB en ram
            iaIndiceDB = new int[cantidad];
            int i = 0;
            if (c.moveToFirst()){
                do{
                   //TablaEquipos: Id, Nombre, NumTel, Sal1, Sal2, Sal3, TipoEquipo
// String stDato = c.getInt(0) + " " + c.getString(0) + c.getInt(1) + " " + c.getString(1);
//                      String stInfo = c.getInt(0) +
//                            " - " + c.getString(1) +
//                            " - " + c.getString(2) +
//                            " - " + c.getString(3) +
//                            " - " + c.getString(4) +
//                            " - " + c.getString(5);
                    iaIndiceDB[i] = c.getInt(0);
                    String sInfo = c.getString(6);
                   sInfo = "Tipo Equipo:  " + sInfo.replace(" - ","\r\n");
//                    sInfo = "Es la plaza principal de la ciudad de San Juan y es donde se encuentra el kilómetro 0 de la provincia";
                    saArreglo[i] = sInfo;
                    String sDatoSoloNombre = c.getString(1);
                    saArregloSoloNombre[i] = sDatoSoloNombre;
                    //aca seleccionar la imagen correspondiente a cada equipo en particular
                    if (sInfo.contains("KP-PE015"))
                        iImagenes[i] = R.drawable.ic_interfaz1;
                    else if(sInfo.contains("KP-PE050"))
                        iImagenes[i] = R.drawable.ic_poste_nuevo_02;
                    else if(sInfo.contains("KP-AL911"))
                        iImagenes[i] = R.drawable.ic_kp_al911;
                    i++;

            }while (c.moveToNext());
            }

            //Creo un adapter para despues setearselo a la lista
            lvAdapter = new ListViewAdapter(this,iImagenes, saArregloSoloNombre, saArreglo);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,saArregloSoloNombre);
            lista.setAdapter(lvAdapter);
        }
    }

    public void borrar_datos_db(int id){
        BaseHelper myBaseHelper = new BaseHelper(this,"DBEquipos",null,1);
        SQLiteDatabase db = myBaseHelper.getWritableDatabase();
        if (db !=null){
            if (db.delete("Equipos", "Id=" + id, null) > 0){
                Toast.makeText(this, "Datos Eliminados Con Exito", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void cargarDatosSeleccion(int id) {
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
                    myEquipoCAPE.setIdDB(c.getInt(0));
                    myEquipoCAPE.setNombre(c.getString(1));
                    myEquipoCAPE.setNumTel(c.getString(2));
                    myEquipoCAPE.setSal1(c.getString(3));
                    myEquipoCAPE.setSal2(c.getString(4));
                    myEquipoCAPE.setSal3(c.getString(5));
                    myEquipoCAPE.setTipoEquipo(c.getString(6));
                }
            } finally {
                c.close();
            }
        }
    }
//Este es el que crea el menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//Cuando selecciona un item del menu
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        if (id == R.id.action_Agregar) {
//            agregar_equipo(this);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //Esta es la clase abstracta para poner en el toolbar el Edit y Delete item
    private ActionMode.Callback amc = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater mi = mode.getMenuInflater();
            mi.inflate(R.menu.menucontexto, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.menu_delete)
            {
                //http://www.journaldev.com/9463/android-alertdialog-example-tutorial
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Eliminar Equipo");
                builder.setMessage("¿Esta Seguro que desea eliminar el Equipo?");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        Toast.makeText(getApplicationContext(), "No is clicked", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        Toast.makeText(getApplicationContext(), "Yes is clicked", Toast.LENGTH_LONG).show();
                        borrar_datos_db(idSeleccionado);
                        cargar_datos_db_listview();

                    }
                });
                builder.show();

                mode.finish();
                return true;
            }
            else if (item.getItemId() == R.id.menu_edit){
                Intent intent = new Intent(MainActivity.this,EditEquipo.class);
                intent.putExtra("Editar",1);
                startActivity(intent);
                mode.finish();
                return true;
            }
            else
                return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
             amc = null;
        }
    };
}
