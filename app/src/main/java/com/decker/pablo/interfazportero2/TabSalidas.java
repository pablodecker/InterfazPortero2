package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabSalidas extends Fragment {

    private static ToggleButton toggleSal1,toggleSal2,toggleSal3;
    private TextView tv1;
    private static TabSalidas ins;
    private static View rootView;
    EquipoCAPE myEquipoCAPE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();

        rootView = inflater.inflate(R.layout.tab_salidas, container, false);

        toggleSal1 = (ToggleButton) rootView.findViewById(R.id.toggleButtonSalida1);
        toggleSal2 = (ToggleButton) rootView.findViewById(R.id.toggleButtonSalida2);
        toggleSal3 = (ToggleButton) rootView.findViewById(R.id.toggleButtonSalida3);

        //TablaEquipos: Id, Nombre, NumTel, Sal1, Sal2, Sal3, TipoEquipo

        toggleSal1.setText(myEquipoCAPE.getSal1());
        toggleSal2.setText(myEquipoCAPE.getSal2());
        toggleSal3.setText(myEquipoCAPE.getSal3());



        toggleSal1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String phoneNo = myEquipoCAPE.getNumTel();
                String sms = "";
                if (toggleSal1.isChecked() == true)
                    sms = "salida1:1";
                else
                    sms = "salida1:0";
                myEquipoCAPE.enviar_sms(phoneNo, sms);
            }

        });

        toggleSal2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String phoneNo = myEquipoCAPE.getNumTel();
                String sms = "";
                if (toggleSal2.isChecked() == true)
                    sms = "salida2:1";
                else
                    sms = "salida2:0";
                myEquipoCAPE.enviar_sms(phoneNo, sms);
            }

        });

        toggleSal3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String phoneNo = myEquipoCAPE.getNumTel();
                String sms = "";
                if (toggleSal3.isChecked() == true)
                    sms = "salida3:1";
                else
                    sms = "salida3:0";
                myEquipoCAPE.enviar_sms(phoneNo, sms);
            }

        });

        if (myEquipoCAPE.getRecibioConfig1() == false)
            setViewAndChildrenEnabled(rootView, false);
        return rootView;

//        return inflater.inflate(R.layout.tab_salidas, container, false);
    }

    //funcion para habilitar o deshabilitar todos los controles
    private static void setViewAndChildrenEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                setViewAndChildrenEnabled(child, enabled);
            }
        }
    }

    public static void SetEstadoButtons(boolean bEstadoSalida1, boolean bEstadoSalida2, boolean bEstadoSalida3 ){
        toggleSal1.setEnabled(true);
        toggleSal2.setEnabled(true);
        toggleSal3.setEnabled(true);
        toggleSal1.setChecked(bEstadoSalida1);
        toggleSal2.setChecked(bEstadoSalida2);
        toggleSal3.setChecked(bEstadoSalida3);
    }
//    public void enviar_sms(String sNumTel, String sTxtSMS){
////        String phoneNo = "3415555781";//textPhoneNo.getText().toString();
////        String sms = "Config: Te1:" + etTe1.getText().toString() + etTe2.getText().toString();
//        try {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(sNumTel, null, sTxtSMS, null, null);
//            Toast.makeText(getContext(), "SMS Enviado - Num:" + sNumTel + "\r\nSMS:" + sTxtSMS, Toast.LENGTH_LONG).show();
//        }
//        catch (Exception e) {
//            Toast.makeText(getContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
//    }

    public static void SetTextoSalidas(String sTxtSalida1, String sTxtSalida2,String sTxtSalida3){


        toggleSal1.setText(sTxtSalida1);
        toggleSal2.setText(sTxtSalida2);
        toggleSal3.setText(sTxtSalida3);
        toggleSal1.setTextOff(sTxtSalida1 + " OFF");
        toggleSal1.setTextOn(sTxtSalida1 + " ON");
        toggleSal2.setTextOff(sTxtSalida2 + " OFF");
        toggleSal2.setTextOn(sTxtSalida2 + " ON");
        toggleSal3.setTextOff(sTxtSalida3 + " OFF");
        toggleSal3.setTextOn(sTxtSalida3 + " ON");
    }
}