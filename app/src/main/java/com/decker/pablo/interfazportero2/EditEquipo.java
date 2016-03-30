package com.decker.pablo.interfazportero2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    // Declare
    static final int PICK_CONTACT=1;
    private static final String TAG = "Pablito";
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;
    private String contactID;     // contacts unique ID

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
            if (iEditar == 1)
            {
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
            if (db.insert("Equipos",null,registronuevo) > 0)
            {
                Toast.makeText(this, "Equipo Guardado Con Exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditEquipo.this,MainActivity.class);
                startActivity(intent);
                this.finish();
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
            if (db.update("Equipos", registronuevo, "Id=" + id, null) > 0)
            {
                Toast.makeText(this, "Equipo Guardado Con Exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditEquipo.this,MainActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
    }

    public void buscar_contacto(View v){
//        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        Intent intent = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, PICK_CONTACT);
        // using native contacts selection
        // Intent.ACTION_PICK = Pick an item from the data, returning what was selected.
//        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
    }
    //Para obtener el numero del contacto seleccionado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case PICK_CONTACT:
                    Cursor cursor = null;
                    try {
                        String phoneNo = null ;
                        String name = null;
                        Uri uri = data.getData();
                        cursor = getContentResolver().query(uri, null, null, null, null);
                        cursor.moveToFirst();
                        int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        phoneNo = cursor.getString(phoneIndex);

                        etNumTel.setText(phoneNo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
//            Log.d(TAG, "Response: " + data.toString());
//            uriContact = data.getData();
//
//            retrieveContactName();
//            retrieveContactNumber();
////            retrieveContactPhoto();
//
//        }
//    }

//    private void retrieveContactPhoto() {
//
//        Bitmap photo = null;
//
//        try {
//            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(),
//                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));
//
//            if (inputStream != null) {
//                photo = BitmapFactory.decodeStream(inputStream);
//                ImageView imageView = (ImageView) findViewById(R.id.img_contact);
//                imageView.setImageBitmap(photo);
//            }
//
//            assert inputStream != null;
//            inputStream.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }



//    @Override
//    public void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//
//        switch (reqCode) {
//            case (PICK_CONTACT) :
//                if (resultCode == Activity.RESULT_OK) {
//                    Uri contactData = data.getData();
//                    Cursor c =  getContentResolver().query(contactData, null, null, null, null);
//                    if (c.moveToFirst()) {
//                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                        String sNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
//                        etNumTel.setText(sNumber);
//                        // TODO Whatever you want to do with the selected contact name.
//                    }
//                }
//                break;
//        }
//    }


}
