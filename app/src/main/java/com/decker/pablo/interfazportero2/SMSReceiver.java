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

            for ( int i = 0; i < messages.length; i++ )
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

            //Si llego un equipo: no lo paso a minuscula
            if (sMensaje.contains("fw:"))
            {
                sMensaje = sMensaje.replace("\r","\r\n");
                TabEstado.SetEstadoEquipo(sMensaje);
                Log.d(TAG, "Numero:" + sNumeroOrigen + " - SMS:" + sMensaje);
                Toast.makeText(context, "Numero:" + sNumeroOrigen + "\r\nSMS:" + sMensaje, Toast.LENGTH_SHORT).show();
                return;
            }
            sMensaje = sMensaje.toLowerCase();

            String sNumEquipoSeleccionado = myEquipoCAPE.getNumTel();
            //remuevo todos los caracteres que no son numeros
            sNumEquipoSeleccionado = sNumEquipoSeleccionado.replaceAll("[\\D]", "");

            String sTipoEquipoSelcccionado = myEquipoCAPE.getTipoEquipo();

            if (sNumeroOrigen.contains(sNumEquipoSeleccionado))
            {
                if (sTipoEquipoSelcccionado.contains("KP-PE015") )//PORTERO COMMAX
                {
                    if ( sMensaje.contains("salida"))
                    {
                        myEquipoCAPE.setbRecibioSalidas(true);
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
                    if ( sMensaje.contains("config") && sMensaje.contains("te1:"))
                    {
                        myEquipoCAPE.setbRecibioConfig1(true);

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

                        if (sTe1.contains(",p"))
                            sTe1 = sTe1.replace(",p","");
                        if (sTe2.contains(",p"))
                            sTe2 = sTe2.replace(",p","");
                        if (sTe3.contains(",p"))
                            sTe3 = sTe3.replace(",p","");
                        if (sTe4.contains(",p"))
                            sTe4 = sTe4.replace(",p","");
                        if (sTe5.contains(",p"))
                            sTe5 = sTe5.replace(",p","");
                        int iSecLlamada1 = 1,iSecLlamada2 = 1 ,iSecLlamada3 = 1,iSecLlamada4 = 1,iSecLlamada5 = 1,
                            iSecAlarma1 = 1,iSecAlarma2 = 1,iSecAlarma3 = 1,iSecAlarma4 = 1,iSecAlarma5 = 1;
                        //secll:1,2,3,4,5
                        if (sMensaje.contains("secll:"))
                        {
                            String sAux = sMensaje.substring(sMensaje.indexOf("secll:")+6,sMensaje.indexOf("secll:")+7);
                            iSecLlamada1 = Integer.parseInt(sAux);
                            sAux = sMensaje.substring(sMensaje.indexOf("secll:")+8,sMensaje.indexOf("secll:")+9);
                            iSecLlamada2 = Integer.parseInt(sAux);
                            sAux = sMensaje.substring(sMensaje.indexOf("secll:")+10,sMensaje.indexOf("secll:")+11 );
                            iSecLlamada3 = Integer.parseInt(sAux);
                            sAux = sMensaje.substring(sMensaje.indexOf("secll:")+12,sMensaje.indexOf("secll:")+13);
                            iSecLlamada4 = Integer.parseInt(sAux);
                            sAux = sMensaje.substring(sMensaje.indexOf("secll:")+14,sMensaje.indexOf("secll:")+15 );
                            iSecLlamada5 = Integer.parseInt(sAux);

                            sAux = sMensaje.substring(sMensaje.indexOf("secal:")+6,sMensaje.indexOf("secal:")+7);
                            iSecAlarma1 = Integer.parseInt(sAux);
                            sAux = sMensaje.substring(sMensaje.indexOf("secal:")+8,sMensaje.indexOf("secal:")+9);
                            iSecAlarma2 = Integer.parseInt(sAux);
                            sAux = sMensaje.substring(sMensaje.indexOf("secal:")+10,sMensaje.indexOf("secal:")+11);
                            iSecAlarma3 = Integer.parseInt(sAux);
                            sAux = sMensaje.substring(sMensaje.indexOf("secal:")+12,sMensaje.indexOf("secal:")+13);
                            iSecAlarma4 = Integer.parseInt(sAux);
                            sAux = sMensaje.substring(sMensaje.indexOf("secal:")+14,sMensaje.indexOf("secal:")+15);
                            iSecAlarma5 = Integer.parseInt(sAux);
                        }

                        TabConfig1.SetControlesInterfazPortero(bHab, sTe1, sTe2, sTe3, sTe4, sTe5,
                                iSecLlamada1, iSecLlamada2, iSecLlamada3, iSecLlamada4, iSecLlamada5,
                                iSecAlarma1, iSecAlarma2, iSecAlarma3, iSecAlarma4, iSecAlarma5);
                    }
                    if ( sMensaje.contains("config") && sMensaje.contains("en1:"))
                    {
                        myEquipoCAPE.setbRecibioConfig2(true);

                        boolean bHabEn1, bHabEn2, bHabEn3, bLlamar1, bLlamar2, bLlamar3,bSMS1, bSMS2, bSMS3;
                        String sTipoEn1,sTipoEn2, sTipoEn3,sTxtSMS1, sTxtSMS2,sTxtSMS3;
                        int iVolTel, iMicTel, iVolFrente, iMicFrente;

                        String sAux = sMensaje.substring(sMensaje.indexOf("en1:")+4,sMensaje.indexOf("en1:")+5);
                        bHabEn1 = sAux.contains("1");
                        sAux = sMensaje.substring(sMensaje.indexOf("en2:") + 4, sMensaje.indexOf("en2:") + 5);
                        bHabEn2 = sAux.contains("1");
                        sAux = sMensaje.substring(sMensaje.indexOf("en3:") + 4, sMensaje.indexOf("en3:") + 5);
                        bHabEn3 = sAux.contains("1");

                        sTipoEn1 = sMensaje.substring(sMensaje.indexOf("en1:") + 6, sMensaje.indexOf("en1:") + 7);
                        sTipoEn2 = sMensaje.substring(sMensaje.indexOf("en2:") + 6, sMensaje.indexOf("en2:") + 7);
                        sTipoEn3 = sMensaje.substring(sMensaje.indexOf("en3:") + 6, sMensaje.indexOf("en3:") + 7);

                        sAux = sMensaje.substring(sMensaje.indexOf("en1:") + 8, sMensaje.indexOf("en1:") + 9);
                        bLlamar1 = sAux.contains("1");
                        sAux = sMensaje.substring(sMensaje.indexOf("en2:") + 8, sMensaje.indexOf("en2:") + 9);
                        bLlamar2 = sAux.contains("1");
                        sAux = sMensaje.substring(sMensaje.indexOf("en3:") + 8, sMensaje.indexOf("en3:") + 9);
                        bLlamar3 = sAux.contains("1");

                        sAux = sMensaje.substring(sMensaje.indexOf("en1:") + 10, sMensaje.indexOf("en1:") + 11);
                        bSMS1 = sAux.contains("1");
                        sAux = sMensaje.substring(sMensaje.indexOf("en2:") + 10, sMensaje.indexOf("en2:") + 11);
                        bSMS2 = sAux.contains("1");
                        sAux = sMensaje.substring(sMensaje.indexOf("en3:") + 10, sMensaje.indexOf("en3:") + 11);
                        bSMS3 = sAux.contains("1");

                        sTxtSMS1 = sMensaje.substring(sMensaje.indexOf("en1:") + 12, sMensaje.indexOf("\r", sMensaje.indexOf("en1:") + 12));
                        sTxtSMS2 = sMensaje.substring(sMensaje.indexOf("en2:") + 12, sMensaje.indexOf("\r", sMensaje.indexOf("en2:") + 12));
                        sTxtSMS3 = sMensaje.substring(sMensaje.indexOf("en3:") + 12, sMensaje.indexOf("\r", sMensaje.indexOf("en3:") + 12));

                        sAux = sMensaje.substring(sMensaje.indexOf("audio:") + 6, sMensaje.indexOf("audio:") + 7);
                        iVolFrente = Integer.parseInt(sAux);
                        sAux = sMensaje.substring(sMensaje.indexOf("audio:") + 8, sMensaje.indexOf("audio:") + 9);
                        iMicFrente = Integer.parseInt(sAux);
                        sAux = sMensaje.substring(sMensaje.indexOf("audio:") + 10, sMensaje.indexOf("audio:") + 11);
                        iVolTel = Integer.parseInt(sAux);
                        sAux = sMensaje.substring(sMensaje.indexOf("audio:") + 12, sMensaje.indexOf("audio:") + 13);
                        iMicTel = Integer.parseInt(sAux);

                        TabConfig2.SetControlesInterfazPortero(bHabEn1, bHabEn2, bHabEn3, bLlamar1, bLlamar2, bLlamar3, bSMS1, bSMS2, bSMS3, sTipoEn1, sTipoEn2, sTipoEn3,
                                sTxtSMS1, sTxtSMS2, sTxtSMS3, iVolTel, iMicTel, iVolFrente, iMicFrente);

                    }
                }
                //poste SOS
                else if (sTipoEquipoSelcccionado.contains("KP-PE050") )
                {
                    if ( sMensaje.contains("config") && sMensaje.contains("te1:"))
                    {
                        myEquipoCAPE.setbRecibioConfig1(true);

                        String sTe1 = "",sTe2 = "",sTe3 = "",sTeR = "",sTcom = "",sTrep = "",sSgn = "",sBat = "",sEmp = "",sID = "";
                        Boolean bHab = false;
                        try{
                            if (sMensaje.contains("hab:si"))
                                bHab = true;
                            String sMensajeBack = sMensaje;
                            if(sMensaje.contains("id:"))
                                sID = sMensaje.substring(sMensaje.indexOf("id:")+3, sMensaje.indexOf("\r",sMensaje.indexOf("id:")+3));
                            if(sMensaje.contains("emp:"))
                                sEmp = sMensaje.substring(sMensaje.indexOf("emp:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("emp:")+4));
                            if(sMensaje.contains("te1:"))
                                sTe1 = sMensaje.substring(sMensaje.indexOf("te1:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("te1:")));
                            if(sMensaje.contains("te2:"))
                                sTe2 = sMensaje.substring(sMensaje.indexOf("te2:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("te2:")));
                            if(sMensaje.contains("te3:"))
                                sTe3 = sMensaje.substring(sMensaje.indexOf("te3:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("te3:")));
                            if(sMensaje.contains("ter:"))
                                sTeR = sMensaje.substring(sMensaje.indexOf("ter:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("ter:")));
                            if(sMensaje.contains("tcom:"))
                                sTcom = sMensaje.substring(sMensaje.indexOf("tcom:")+5, sMensaje.indexOf("s",sMensaje.indexOf("tcom:")+5));
                            if(sMensaje.contains("bat:"))
                                sBat = sMensaje.substring(sMensaje.indexOf("bat:")+4, sMensaje.indexOf("v",sMensaje.indexOf("bat:")+4));
                            if(sMensaje.contains("sgn:"))
                                sSgn = sMensaje.substring(sMensaje.indexOf("sgn:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("sgn:")+4));
                            if(sMensaje.contains("trsms:")) {
                                sTrep = sMensaje.substring(sMensaje.indexOf("trsms:") + 6, sMensaje.indexOf("m\r", sMensaje.indexOf("trsms:" + 5) ));
                            }
                        }
                        catch(Exception ex){
                            Toast.makeText(context,"Exeption:" + ex.toString() , Toast.LENGTH_SHORT).show();
                        }

                        String sAux = sMensaje.substring(sMensaje.indexOf("vol:")+4,sMensaje.indexOf("vol:")+5);
                        int iVol = Integer.parseInt(sAux);
                        sAux = sMensaje.substring(sMensaje.indexOf("mic:")+4,sMensaje.indexOf("mic:")+5);
                        int iMic = Integer.parseInt(sAux);

                        TabConfig1.SetControlesPosteSOS(bHab, sTe1, sTe2, sTe3, sTeR, iMic, iVol,sTcom, sTrep, sSgn,sBat, sEmp, sID);
                    }
                }
                //KP-AL911
                else if (sTipoEquipoSelcccionado.contains("KP-AL911") )
                {
                    if ( sMensaje.contains("config") && sMensaje.contains("te1:"))
                    {
                        String sTe1 = "",sTe2 = "",sTe3 = "",sTe4 = "",sTe5 = "",sSgn = "",sBat = "", sTRepe = "", sNombre = "";
                        Boolean bHab = false;
                        if (sMensaje.contains("hab:si"))
                            bHab = true;
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
                        if(sMensaje.contains("nombre:"))
                            sNombre = sMensaje.substring(sMensaje.indexOf("nombre:")+7, sMensaje.indexOf("\r",sMensaje.indexOf("nombre:")+7));

                        if(sMensaje.contains("bat:"))
                            sBat = sMensaje.substring(sMensaje.indexOf("bat:")+4, sMensaje.indexOf("v",sMensaje.indexOf("bat:")+4));
                        if(sMensaje.contains("sgn:"))
                            sSgn = sMensaje.substring(sMensaje.indexOf("sgn:")+4, sMensaje.indexOf("\r",sMensaje.indexOf("sgn:")+4));
                       if(sMensaje.contains("trepe:"))
                           sTRepe = sMensaje.substring(sMensaje.indexOf("trepe:")+6, sMensaje.indexOf("\r",sMensaje.indexOf("trepe:")+6));

                        TabConfig1.SetControlesAlarma(bHab, sTe1, sTe2, sTe3, sTe4,sTe5, sSgn,sBat,sTRepe, sNombre);
                    }
                    if ( sMensaje.contains("config") && sMensaje.contains("ip:"))
                    {
                        String sIP = "",sPuerto = "",sTReporte = "";
                        if(sMensaje.contains("ip:"))
                            sIP = sMensaje.substring(sMensaje.indexOf("ip:")+3, sMensaje.indexOf("\r",sMensaje.indexOf("ip:")+3));
                        if(sMensaje.contains("puerto:"))
                            sPuerto = sMensaje.substring(sMensaje.indexOf("puerto:")+7, sMensaje.indexOf("\r",sMensaje.indexOf("puerto:")+7));
                        if(sMensaje.contains("trgprs:"))
                            sTReporte = sMensaje.substring(sMensaje.indexOf("trgprs:")+7, sMensaje.indexOf("\r",sMensaje.indexOf("trgprs:")+7));
                        TabConfig2.SetControlesAlarma(sIP,sPuerto,sTReporte);
                    }
                }
            }

            Log.d(TAG, "Numero:" + sNumeroOrigen + " - SMS:" + sMensaje);
            Toast.makeText(context, "Numero:" + sNumeroOrigen + "\r\nSMS:" + sMensaje, Toast.LENGTH_SHORT).show();
        }
    }

}
