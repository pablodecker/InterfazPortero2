package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabComandos extends Fragment {

    private static Button btnConectar, btnLlamar;
    private static View rootView;
    EquipoCAPE myEquipoCAPE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();

        rootView = inflater.inflate(R.layout.tab_comandos, container, false);

        btnConectar = (Button)rootView.findViewById(R.id.buttonConectar);
        btnLlamar = (Button)rootView.findViewById(R.id.buttonLlamar);


        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String phoneNo = myEquipoCAPE.getNumTel();
                enviar_sms(phoneNo, "Conectar:");
            }
        });

        btnLlamar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String phoneNo = myEquipoCAPE.getNumTel();
                enviar_sms(phoneNo, "Llamar:");
            }
        });
        return rootView;
    }


    public void enviar_sms(String sNumTel, String sTxtSMS){
//        String phoneNo = "3415555781";//textPhoneNo.getText().toString();
//        String sms = "Config: Te1:" + etTe1.getText().toString() + etTe2.getText().toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sNumTel, null, sTxtSMS, null, null);
            Toast.makeText(getContext(), "SMS Enviado - Num:" + sNumTel + "\r\nSMS:" + sTxtSMS, Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}