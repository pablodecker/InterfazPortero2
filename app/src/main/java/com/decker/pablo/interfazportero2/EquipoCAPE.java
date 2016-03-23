package com.decker.pablo.interfazportero2;

import android.app.Application;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Pablo on 26/01/2016.
 * Esta clase es para setear y obtener todos los datos acerca de un equipo en particular
 */
public class EquipoCAPE extends Application{

    private String sNombre, sNumTel, sSal1, sSal2, sSal3, sTipoEquipo;
    private int iIdDB;
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
//    public int getTipoEquipo(){
//        return iTipoEquipo;
//    }
    public void enviar_sms(String sNumTel, String sTxtSMS)
    {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sNumTel, null, sTxtSMS, null, null);
            Toast.makeText(getApplicationContext(), "SMS Enviado - Num:" + sNumTel + "\r\nSMS:" + sTxtSMS, Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
