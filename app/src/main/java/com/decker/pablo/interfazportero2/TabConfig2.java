package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabConfig2 extends Fragment {
    EquipoCAPE myEquipoCAPE;
    String[] saListaEquipos;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Obtengo la lista de equipos de un array
        saListaEquipos = getResources().getStringArray(R.array.lista_de_equipos);
        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();
        String sInfo = saListaEquipos[myEquipoCAPE.getTipoEquipo()];// + "\r\n" + c.getString(2);
        if (sInfo.contains("KP-PE015"))
            rootView = inflater.inflate(R.layout.tab_config2_portero_cfgadmin, container, false);
        else if(sInfo.contains("KP-AL911"))
            rootView = inflater.inflate(R.layout.tab_config2_alarma_gprs, container, false);
        else
            rootView = inflater.inflate(R.layout.tab_config2_alarma_gprs, container, false);

        return rootView;
    }
}
