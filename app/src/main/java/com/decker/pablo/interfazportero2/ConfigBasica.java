package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ConfigBasica extends AppCompatActivity {
    EditText etTe1,etTe2,etTe3,etTe4,etTe5;
    Switch swHab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_basica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etTe1 = (EditText) findViewById(R.id.etTelefono1);
        etTe2 = (EditText) findViewById(R.id.etTelefono2);
        etTe3 = (EditText) findViewById(R.id.etTelefono3);
        etTe4 = (EditText) findViewById(R.id.etTelefono4);
        etTe5 = (EditText) findViewById(R.id.etTelefono5);
        swHab = (Switch) findViewById(R.id.switchHabilitacion);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    public void enviar_sms_config(View v){
        String phoneNo = "3415555781";//textPhoneNo.getText().toString();
        String sms = "Config: Te1:" + etTe1.getText().toString() + etTe2.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
