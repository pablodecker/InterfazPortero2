package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabConfig1 extends Fragment {
    private static EditText etTe1,etTe2,etTe3,etTe4,etTe5;
    private static Switch swHab;
    private static Spinner spSecLlamada1,spSecLlamada2,spSecLlamada3,spSecLlamada4,spSecLlamada5,
                            spSecAlarma1,spSecAlarma2,spSecAlarma3,spSecAlarma4,spSecAlarma5;
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

        String[] listaNumeros = {"1","2","3","4","5"};
        ArrayAdapter adapterSec = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaNumeros);

        spSecLlamada1 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_1);
        spSecLlamada1.setAdapter(adapterSec);

        spSecLlamada2 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_2);
        spSecLlamada2.setAdapter(adapterSec);

        spSecLlamada3 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_3);
        spSecLlamada3.setAdapter(adapterSec);

        spSecLlamada4 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_4);
        spSecLlamada4.setAdapter(adapterSec);

        spSecLlamada5 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_5);
        spSecLlamada5.setAdapter(adapterSec);

        spSecAlarma1 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_1);
        spSecAlarma1.setAdapter(adapterSec);

        spSecAlarma2 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_2);
        spSecAlarma2.setAdapter(adapterSec);

        spSecAlarma3 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_3);
        spSecAlarma3.setAdapter(adapterSec);

        spSecAlarma4 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_4);
        spSecAlarma4.setAdapter(adapterSec);

        spSecAlarma5 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_5);
        spSecAlarma5.setAdapter(adapterSec);

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


    public static void SetControles(boolean bSwitchHab, String sTe1, String sTe2, String sTe3, String sTe4, String sTe5,
                                    int iSecLlamada1,int iSecLlamada2,int iSecLlamada3,int iSecLlamada4,int iSecLlamada5,
                                    int iSecAlarma1,int iSecAlarma2,int iSecAlarma3,int iSecAlarma4,int iSecAlarma5 )
    {
        swHab.setChecked(bSwitchHab);
        etTe1.setText(sTe1);
        etTe2.setText(sTe2);
        etTe3.setText(sTe3);
        etTe4.setText(sTe4);
        etTe5.setText(sTe5);
        spSecLlamada1.setSelection(iSecLlamada1-1);
        spSecLlamada2.setSelection(iSecLlamada2-1);
        spSecLlamada3.setSelection(iSecLlamada3-1);
        spSecLlamada4.setSelection(iSecLlamada4-1);
        spSecLlamada5.setSelection(iSecLlamada5-1);
        spSecAlarma1.setSelection(iSecAlarma1-1);
        spSecAlarma2.setSelection(iSecAlarma2-1);
        spSecAlarma3.setSelection(iSecAlarma3-1);
        spSecAlarma4.setSelection(iSecAlarma4-1);
        spSecAlarma5.setSelection(iSecAlarma5-1);
    }

}
