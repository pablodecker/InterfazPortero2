package com.decker.pablo.interfazportero2;

import android.app.Application;

/**
 * Created by Pablo on 26/01/2016.
 * Esta clase es para setear y obtener todos los datos acerca de un equipo en particular
 */
public class EquipoCAPE extends Application{

    private String sNombre, sNumTel, sSal1, sSal2, sSal3;
    private int iIdDB, iTipoEquipo;
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

    public void setTipoEquipo(int iTipoEquipo){
        this.iTipoEquipo = iTipoEquipo;
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
    public int getTipoEquipo(){
        return iTipoEquipo;
    }

}
