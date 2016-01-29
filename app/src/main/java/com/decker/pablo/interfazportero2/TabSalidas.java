package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabSalidas extends Fragment {

    private ToggleButton toggleSal1,toggleSal2,toggleSal3;
    View rootView;
    EquipoCAPE myEquipoCAPE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tab_salidas, container, false);

        toggleSal1 = (ToggleButton) rootView.findViewById(R.id.toggleButtonSalida1);
        toggleSal2 = (ToggleButton) rootView.findViewById(R.id.toggleButtonSalida2);
        toggleSal3 = (ToggleButton) rootView.findViewById(R.id.toggleButtonSalida3);

        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();
        //TablaEquipos: Id, Nombre, NumTel, Sal1, Sal2, Sal3, TipoEquipo
        toggleSal1.setTextOff(myEquipoCAPE.getSal1() + " OFF");
        toggleSal1.setTextOn(myEquipoCAPE.getSal1() + " ON");
        toggleSal2.setTextOff(myEquipoCAPE.getSal2() + " OFF");
        toggleSal2.setTextOn(myEquipoCAPE.getSal2() + " ON");
        toggleSal3.setTextOff(myEquipoCAPE.getSal3() + " OFF");
        toggleSal3.setTextOn(myEquipoCAPE.getSal3() + " ON");

        return rootView;

//        return inflater.inflate(R.layout.tab_salidas, container, false);
    }
}