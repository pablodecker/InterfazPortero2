package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.Switch;


public class EquipoParticular extends AppCompatActivity {
    EditText etTe1,etTe2,etTe3,etTe4,etTe5;
    Switch swHab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_particular);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etTe1 = (EditText) findViewById(R.id.etTelefono1);
        etTe2 = (EditText) findViewById(R.id.etTelefono2);
        etTe3 = (EditText) findViewById(R.id.etTelefono3);
        etTe4 = (EditText) findViewById(R.id.etTelefono4);
        etTe5 = (EditText) findViewById(R.id.etTelefono5);
        swHab = (Switch) findViewById(R.id.switchHabilitacion);

        //esto es para crear los Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Estado"));
        tabLayout.addTab(tabLayout.newTab().setText("Sal"));
        tabLayout.addTab(tabLayout.newTab().setText("Conf1"));
        tabLayout.addTab(tabLayout.newTab().setText("Conf2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        //esta es la clase que cree para crear un adaptador y luego pasar
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


//        etTe1.setText("3114563781");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
