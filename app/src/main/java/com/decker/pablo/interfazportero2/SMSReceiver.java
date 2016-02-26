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

    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "** Recibi algun SMS");
        Toast.makeText(context, "Recibi Algo", Toast.LENGTH_SHORT).show();
        Bundle myBundle = intent.getExtras();
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

            boolean bSalida1 = false, bSalida2 = false, bSalida3 = false;
            if ( sMensaje.contains("salida"))
            {
                Log.d(TAG, "Conmutar salidas");
                if (sMensaje.contains("salida1:1"))
                    bSalida1 = true;
                if (sMensaje.contains("salida2:1"))
                    bSalida2 = true;
                if (sMensaje.contains("salida3:1"))
                    bSalida3 = true;
                TabSalidas.SetEstadoButtons(bSalida1,bSalida2,bSalida3);
            }
            // log .d = debug
            Log.d(TAG, "Numero:" + sNumeroOrigen + " - SMS:" + sMensaje);
            Toast.makeText(context, "Numero:" + sNumeroOrigen + "\r\nSMS:" + sMensaje, Toast.LENGTH_SHORT).show();
        }
    }

}
