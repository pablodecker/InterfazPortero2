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
    String sTipoEquipo = "";
    private String TAG = "Pablito";
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
        sTipoEquipo = myEquipoCAPE.getTipoEquipo();
        Log.d(TAG, "TipoEquipo:" + sTipoEquipo);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(myEquipoCAPE.getNombre());
        setSupportActionBar(toolbar);
        //        para crear el boton hacia atras (Back)(tambien agregar en el manifest el parentActivityName ....
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        myEquipoCAPE.setbRecibioEstado(false);
        myEquipoCAPE.setbRecibioSalidas(false);
        myEquipoCAPE.setbRecibioConfig1(false);
        myEquipoCAPE.setbRecibioConfig2(false);
        myEquipoCAPE.setbRecibioComandos(false);

//        etTe1 = (EditText) findViewById(R.id.etTelefono1);
//        etTe2 = (EditText) findViewById(R.id.etTelefono2);
//        etTe3 = (EditText) findViewById(R.id.etTelefono3);
//        etTe4 = (EditText) findViewById(R.id.etTelefono4);
//        etTe5 = (EditText) findViewById(R.id.etTelefono5);
//        swHab = (Switch)   findViewById(R.id.switchHabilitacion);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        //esto es para crear los Tabs
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (sTipoEquipo.contains("KP-PE015")) //INTERFAZ COMMAX
        {
            tabLayout.addTab(tabLayout.newTab().setText("EST"));
            tabLayout.addTab(tabLayout.newTab().setText("SAL"));
            tabLayout.addTab( tabLayout.newTab().setText("CFG").setIcon(R.drawable.ic_action_action_settings) );
            tabLayout.addTab(tabLayout.newTab().setText("CFG A").setIcon(R.drawable.ic_action_action_settings));
            tabLayout.addTab(tabLayout.newTab().setText("CMD"));
            //esta es la clase que cree para crear un adaptador y luego pasar
            final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            //le seteo el adaptador al viewpager
            viewPager.setAdapter(adapter);
        }
        else if(sTipoEquipo.contains("KP-AL911"))
        {
            tabLayout.addTab(tabLayout.newTab().setText("EST"));
            tabLayout.addTab(tabLayout.newTab().setText("SAL"));
            tabLayout.addTab( tabLayout.newTab().setText("CFG").setIcon(R.drawable.ic_action_action_settings));
            tabLayout.addTab(tabLayout.newTab().setText("GPRS").setIcon(R.drawable.ic_action_communication_import_export));
            tabLayout.addTab(tabLayout.newTab().setText("CMD"));
            //esta es la clase que cree para crear un adaptador y luego pasar
            final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            //le seteo el adaptador al viewpager
            viewPager.setAdapter(adapter);
        }
        else if (sTipoEquipo.contains("KP-PE050"))  //Poste SOS
        {
            tabLayout.addTab(tabLayout.newTab().setText("EST"));
            tabLayout.addTab( tabLayout.newTab().setText("CFG").setIcon(R.drawable.ic_action_action_settings) );
            //esta es la clase que cree para crear un adaptador y luego pasar
            final PagerAdapterPostes adapter = new PagerAdapterPostes(getSupportFragmentManager(), tabLayout.getTabCount());
            //le seteo el adaptador al viewpager
            viewPager.setAdapter(adapter);
        }
        else
        {
            tabLayout.addTab(tabLayout.newTab().setText("EST"));
            tabLayout.addTab(tabLayout.newTab().setText("SAL"));
            tabLayout.addTab( tabLayout.newTab().setText("CFG").setIcon(R.drawable.ic_action_action_settings) );
            tabLayout.addTab(tabLayout.newTab().setText("CFG2").setIcon(R.drawable.ic_action_action_settings));
            tabLayout.addTab(tabLayout.newTab().setText("CMD"));
            //esta es la clase que cree para crear un adaptador y luego pasar
            final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            //le seteo el adaptador al viewpager
            viewPager.setAdapter(adapter);
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //le agrego el tabLayout al viewpager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                iTabPosition = tab.getPosition();
                switch (tab.getPosition())
                {
                    case 0:
//                        Toast.makeText(getApplicationContext(), "Tab 1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if (sTipoEquipo.contains("KP-PE015") ) //INTERFAZ COMMAX
                        {
//                            Toast.makeText(getApplicationContext(), "Set Salidas", Toast.LENGTH_SHORT).show();
                            TabSalidas.SetTextoSalidas(myEquipoCAPE.getSal1(), myEquipoCAPE.getSal2(), myEquipoCAPE.getSal3());
                        }
                        break;
                    case 2:
//                        Toast.makeText(getApplicationContext(), "Tab 3", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
//                        Toast.makeText(getApplicationContext(), "Tab 4", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
//                        Toast.makeText(getApplicationContext(), "Tab 5", Toast.LENGTH_SHORT).show();
                        break;
                }
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
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Toast.makeText(getApplicationContext(), "Consultar: " + Integer.toString(iTabPosition), Toast.LENGTH_LONG).show();
                if (sTipoEquipo.contains("KP-PE050"))  //Poste SOS
                {   //tengo solo 2 pestanas en los postes
                    //EST | CFG
                    if (iTabPosition == 0)
                    {
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Equipo?");
                    }
                    else
                    {
//                        view.setEnabled(false); // Or whatever you want to do with the view.
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Config?");
                    }

                }
                else if (sTipoEquipo.contains("KP-PE015"))//INTERFAZ PORTERO
                {
                    if (iTabPosition == 0)
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Equipo?");
                    else if (iTabPosition == 1)
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Salidas?");
                    else if (iTabPosition == 2)
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Config?");
                    else if (iTabPosition == 3)
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Cfgadmin?");
                }
                else if (sTipoEquipo.contains("KP-AL911"))
                {
                    if (iTabPosition == 0)
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Equipo?");
                    else if (iTabPosition == 1)
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Salidas?");
                    else if (iTabPosition == 2)
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Config?");
                    else if (iTabPosition == 3)
                        myEquipoCAPE.enviar_sms(myEquipoCAPE.getNumTel(), "Confgprs?");
                }
            }
        });
    }
}
