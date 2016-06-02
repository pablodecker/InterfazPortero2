package com.decker.pablo.interfazportero2;

import android.app.Application;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Pablo on 26/01/2016.
 * Esta clase es para setear y obtener todos los datos acerca de un equipo en particular
 */
public class EquipoCAPE extends Application{

    private String sNombre, sNumTel, sSal1, sSal2, sSal3, sTipoEquipo;
    private String TAG = "Pablito";
    private int iIdDB;
    private boolean bRecibioEstado, bRecibioSalidas, bRecibioConfig1, bRecibioConfig2, bRecibioComandos,
            bEnvioEstado, bEnvioSalidas, bEnvioConfig1, bEnvioConfig2, bEnvioComandos;
    //TablaEquipos: Id, Nombre, NumTel, Sal1, Sal2, Sal3, TipoEquipo
    public void setIdDB(int iIdDB){
        this.iIdDB = iIdDB;
    }
    public void setNombre(String sNombre){
        this.sNombre = sNombre;
    }
    public void setNumTel(String sNumTel){
        this.sNumTel = sNumTel;
    }
    public void setSal1(String sSal1){
        this.sSal1 = sSal1;
    }
    public void setSal2(String sSal2){
        this.sSal2 = sSal2;
    }
    public void setSal3(String sSal3){
        this.sSal3 = sSal3;
    }

    public void setbEnvioEstado(boolean bEnvioEstado){
        this.bEnvioEstado = bEnvioEstado;
    }
    public void setbEnvioSalidas(boolean bEnvioSalidas){
        this.bEnvioSalidas = bEnvioSalidas;
    }
    public void setbEnvioConfig1(boolean bEnvioConfig1){
        this.bEnvioConfig1 = bEnvioConfig1;
    }
    public void setbEnvioConfig2(boolean bEnvioConfig2){
        this.bEnvioConfig2 = bEnvioConfig2;
    }
    public void setbEnvioComandos(boolean bEnvioComandos){
        this.bEnvioComandos = bEnvioComandos;
    }

    public void setbRecibioEstado(boolean bRecibioEstado){
        this.bRecibioEstado = bRecibioEstado;
    }
    public void setbRecibioSalidas(boolean bRecibioSalidas){
        this.bRecibioSalidas = bRecibioSalidas;
    }
    public void setbRecibioConfig1(boolean bRecibioConfig1){
        this.bRecibioConfig1 = bRecibioConfig1;
    }
    public void setbRecibioConfig2(boolean bRecibioConfig2){
        this.bRecibioConfig2 = bRecibioConfig2;
    }
    public void setbRecibioComandos(boolean bRecibioComandos){
        this.bRecibioComandos = bRecibioComandos;
    }

//    public void setTipoEquipo(int iTipoEquipo){
//        this.iTipoEquipo = iTipoEquipo;
//    }
    public void setTipoEquipo(String sTipoEquipo){
        this.sTipoEquipo = sTipoEquipo;
    }

    public int getIdDB() {
        return iIdDB;
    }
    public String getNombre(){
        return sNombre;
    }
    public String getNumTel(){
        return sNumTel;
    }
    public String getSal1(){
        return sSal1;
    }
    public String getSal2(){
        return sSal2;
    }
    public String getSal3(){
        return sSal3;
    }
    public String getTipoEquipo(){
        return sTipoEquipo;
    }

    public boolean getEnvioEstado(){
        return bEnvioEstado;
    }
    public boolean getEnvioConfig1(){
        return bEnvioConfig1;
    }
    public boolean getEnvioConfig2(){
        return bEnvioConfig2;
    }
    public boolean getEnvioSalidas(){
        return bEnvioSalidas;
    }
    public boolean getEnvioComandos(){
        return bEnvioComandos;
    }

    public boolean getRecibioEstado(){
        return bRecibioEstado;
    }
    public boolean getRecibioConfig1(){
        return bRecibioConfig1;
    }
    public boolean getRecibioConfig2(){
        return bRecibioConfig2;
    }
    public boolean getRecibioSalidas(){
        return bRecibioSalidas;
    }
    public boolean getRecibioComandos(){
        return bRecibioComandos;
    }




    public void enviar_sms(String sNumTel, String sTxtSMS)
    {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sNumTel, null, sTxtSMS, null, null);
            Toast.makeText(getApplicationContext(), "SMS Enviado - Num:" + sNumTel + "\r\nSMS:" + sTxtSMS, Toast.LENGTH_LONG).show();
            Log.d(TAG,  "SMS Enviado - Num:" + sNumTel + " - SMS:" + sTxtSMS);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
