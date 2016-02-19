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
        String strMessage = "";
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
                strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                strMessage += " : ";
                strMessage += messages[i].getMessageBody();
                strMessage += "\n";
            }

            if ( strMessage.contains("sal")){


            }
            // log .d = debug
            Log.d(TAG, strMessage);
            Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
        }
    }

}
