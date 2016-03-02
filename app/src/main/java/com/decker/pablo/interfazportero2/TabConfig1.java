package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabConfig1 extends Fragment {
    private static EditText etTe1,etTe2,etTe3,etTe4,etTe5;
    private static Switch swHab;
    EquipoCAPE myEquipoCAPE;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tab_config1, container, false);

        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();
        String sTipoEquipo = myEquipoCAPE.getTipoEquipo();

        etTe1 = (EditText) rootView.findViewById(R.id.etTelefono1);
        etTe2 = (EditText) rootView.findViewById(R.id.etTelefono2);
        etTe3 = (EditText) rootView.findViewById(R.id.etTelefono3);
        etTe4 = (EditText) rootView.findViewById(R.id.etTelefono4);
        etTe5 = (EditText) rootView.findViewById(R.id.etTelefono5);
        swHab = (Switch) rootView.findViewById(R.id.switchHabilitacion);
        return rootView;
//        return inflater.inflate(R.layout.tab_config1, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b = (Button) rootView.findViewById(R.id.buttonConfigurar);
        b.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String phoneNo = myEquipoCAPE.getNumTel();
                String sHab = " Hab:No";
                if(swHab.isChecked())
                    sHab = " Hab:Si";
                String sms = "Config:" + sHab +
                        " Te1:" + etTe1.getText().toString() +
                        " Te2:" + etTe2.getText().toString() +
                        " Te3:" + etTe3.getText().toString() +
                        " Te4:" + etTe4.getText().toString() +
                        " Te5:" + etTe5.getText().toString();
                enviar_sms_config(phoneNo, sms);
            }

        });
    }

    public void enviar_sms_config(String sNumero, String sTextoSMS){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sNumero, null, sTextoSMS, null, null);
            Toast.makeText(getActivity(),"SMS Enviado",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(getActivity(),"No se pudo enviar el SMS",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    public static void SetControles(boolean bSwitchHab, String sTe1, String sTe2, String sTe3, String sTe4, String sTe5){
        swHab.setChecked(bSwitchHab);
        etTe1.setText(sTe1);
        etTe2.setText(sTe2);
        etTe3.setText(sTe3);
        etTe4.setText(sTe4);
        etTe5.setText(sTe5);
    }

}
