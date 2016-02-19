package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class EquipoParticular extends AppCompatActivity {
    EditText etTe1,etTe2,etTe3,etTe4,etTe5;
    Switch swHab;
    TabLayout tabLayout;
    int iTabPosition, idEquipoDB;
    EquipoCAPE myEquipoCAPE;
    private String TAG = "Pablito";
    private String[] listaEquipos;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_particular);

        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getApplicationContext();
        //TablaEquipos: Id, Nombre, NumTel, Sal1, Sal2, Sal3, TipoEquipo
        Log.d(TAG, "NombreLeido:" + myEquipoCAPE.getNombre());
        Log.d(TAG, "NumTel:" + myEquipoCAPE.getNumTel());
        Log.d(TAG, "Sal1:" + myEquipoCAPE.getSal1());
        Log.d(TAG, "Sal2:" + myEquipoCAPE.getSal2());
        Log.d(TAG, "Sal3:" + myEquipoCAPE.getSal3());
        Log.d(TAG, "TipoEquipo:" + Integer.toString(myEquipoCAPE.getTipoEquipo()));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(myEquipoCAPE.getNombre());
        setSupportActionBar(toolbar);
        //        para crear el boton hacia atras (Back)(tambien agregar en el manifest el parentActivityName ....
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        listaEquipos = getResources().getStringArray(R.array.lista_de_equipos);

        etTe1 = (EditText) findViewById(R.id.etTelefono1);
        etTe2 = (EditText) findViewById(R.id.etTelefono2);
        etTe3 = (EditText) findViewById(R.id.etTelefono3);
        etTe4 = (EditText) findViewById(R.id.etTelefono4);
        etTe5 = (EditText) findViewById(R.id.etTelefono5);
        swHab = (Switch)   findViewById(R.id.switchHabilitacion);

        //esto es para crear los Tabs
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Estado"));
        tabLayout.addTab(tabLayout.newTab().setText("Sal"));
        tabLayout.addTab(tabLayout.newTab().setText("Conf1"));
        tabLayout.addTab(tabLayout.newTab().setText("Conf2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        //esta es la clase que cree para crear un adaptador y luego pasar
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        //le seteo el adaptador al viewpager
        viewPager.setAdapter(adapter);
        //le agrego el tabLayout al viewpager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                  iTabPosition = tab.getPosition();
//                switch (tab.getPosition()) {
//                    case 0:
//                        Toast.makeText(getApplicationContext(), "Tab 1", Toast.LENGTH_LONG).show();
//                        break;
//                    case 1:
//                        Toast.makeText(getApplicationContext(), "Tab 2", Toast.LENGTH_LONG).show();
//                        break;
//                    case 2:
//                        Toast.makeText(getApplicationContext(), "Tab 3", Toast.LENGTH_LONG).show();
//                        break;
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


//        etTe1.setText("3114563781");
        //BOTON "?"
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Toast.makeText(getApplicationContext(), "Consultar: " + Integer.toString(iTabPosition), Toast.LENGTH_LONG).show();

                //Estados
                if (iTabPosition == 0){

                }
                //Salidas
                else if (iTabPosition == 1){
                    enviar_sms(myEquipoCAPE.getNumTel(), "Salidas?");
                }
                //Config1
                else if (iTabPosition == 2){
                    enviar_sms(myEquipoCAPE.getNumTel(), "Config?");
                }
                //Config2
                else if (iTabPosition == 3){

                }

            }
        });
    }

    public void enviar_sms(String sNumTel, String sTxtSMS){
//        String phoneNo = "3415555781";//textPhoneNo.getText().toString();
//        String sms = "Config: Te1:" + etTe1.getText().toString() + etTe2.getText().toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sNumTel, null, sTxtSMS, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
