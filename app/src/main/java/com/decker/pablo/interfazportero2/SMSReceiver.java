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

    private String TAG = SMSReceiver.class.getSimpleName();

    public SMSReceiver() {
    }
    public void onReceive(Context context, Intent intent)
    {
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

            Log.e("SMS", strMessage);
            Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
        }
    }
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // Get the data (SMS data) bound to intent
//        Bundle bundle = intent.getExtras();
//
//        SmsMessage[] msgs = null;
//
//        String str = "";
//
//        if (bundle != null) {
//            // Retrieve the SMS Messages received
//            Object[] pdus = (Object[]) bundle.get("pdus");
//            msgs = new SmsMessage[pdus.length];
//
//            // For every SMS message received
//            for (int i=0; i < msgs.length; i++) {
//                // Convert Object array
//                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                // Sender's phone number
//                str += "SMS from " + msgs[i].getOriginatingAddress() + " : ";
//                // Fetch the text message
//                str += msgs[i].getMessageBody().toString();
//                // Newline <img src="http://codetheory.in/wp-includes/images/smilies/simple-smile.png" alt=":-)" class="wp-smiley" style="height: 1em; max-height: 1em;">
//                str += "\n";
//            }
//
//            // Display the entire SMS Message
//            Log.d(TAG, str);
//        }
//    }
}
