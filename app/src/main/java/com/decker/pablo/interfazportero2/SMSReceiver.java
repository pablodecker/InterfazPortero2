package com.decker.pablo.interfazportero2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import android.widget.Toast;

/**
 * Created by Pablo on 11/01/2016.
 */
public class SMSReceiver extends BroadcastReceiver {

    private String TAG = "Pablito";
    EquipoCAPE myEquipoCAPE;

    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "** Recibi algun SMS");
        Toast.makeText(context, "Recibi Algo", Toast.LENGTH_SHORT).show();
        Bundle myBundle = intent.getExtras();

        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)context.getApplicationContext();


        SmsMessage [] messages = null;
        String sMensaje = "",sNumeroOrigen = "";
        if (myBundle != null)
        {
            Object [] pdus = (Object[]) myBundle.get("pdus");

            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = myBundle.getString("format");
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                }
                else {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                sNumeroOrigen =  messages[i].getOriginatingAddress();
                sMensaje =  messages[i].getMessageBody();
            }
            // log .d = debug
            Log.d(TAG, "Llego antes de lowercase");
            sMensaje = sMensaje.toLowerCase();

            String sNumEquipoSeleccionado = myEquipoCAPE.getNumTel();
            String sTipoEquipoSelcccionado = myEquipoCAPE.getTipoEquipo();
            if (sNumeroOrigen.contains(sNumEquipoSeleccionado)){

                if (sTipoEquipoSelcccionado.contains("KP-PE015") ){

                    if ( sMensaje.contains("salida"))
                    {
                        boolean bSalida1 = false, bSalida2 = false, bSalida3 = false;
                        Log.d(TAG, "Conmutar salidas");
                        if (sMensaje.contains("salida1:1"))
                            bSalida1 = true;
                        if (sMensaje.contains("salida2:1"))
                            bSalida2 = true;
                        if (sMensaje.contains("salida3:1"))
                            bSalida3 = true;
                        TabSalidas.SetEstadoButtons(bSalida1,bSalida2,bSalida3);
                    }
                    if ( sMensaje.contains("config")){
                        String sTe1 = "",sTe2 = "",sTe3 = "",sTe4 = "",sTe5 = "";
                        Boolean bHab = false;
                        if (sMensaje.contains("hab:si"))
                            bHab = true;
                        String sMensajeBack = sMensaje;
                        if(sMensaje.contains("te1:"))
                            sTe1 = sMensaje.substring(sMensaje.indexOf("te1:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("te1:")));
                        if(sMensaje.contains("te2:"))
                            sTe2 = sMensaje.substring(sMensaje.indexOf("te2:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("te2:")));
                        if(sMensaje.contains("te3:"))
                            sTe3 = sMensaje.substring(sMensaje.indexOf("te3:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("te3:")));
                        if(sMensaje.contains("te4:"))
                            sTe4 = sMensaje.substring(sMensaje.indexOf("te4:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("te4:")));
                        if(sMensaje.contains("te5:"))
                            sTe5 = sMensaje.substring(sMensaje.indexOf("te5:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("te5:")));


                        TabConfig1.SetControles(bHab,sTe1, sTe2, sTe3, sTe4, sTe5);
                    }
                }

            }





            // log .d = debug
            Log.d(TAG, "Numero:" + sNumeroOrigen + " - SMS:" + sMensaje);
            Toast.makeText(context, "Numero:" + sNumeroOrigen + "\r\nSMS:" + sMensaje, Toast.LENGTH_SHORT).show();
        }
    }

}
