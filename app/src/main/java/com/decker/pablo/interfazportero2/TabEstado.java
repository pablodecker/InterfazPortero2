package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabEstado extends Fragment {

    private static TextView tvEstadoEquipoPoste;
    String sTipoEquipo;
    EquipoCAPE myEquipoCAPE;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();
        sTipoEquipo = myEquipoCAPE.getTipoEquipo();

        rootView = inflater.inflate(R.layout.tab_estado_equipo, container, false);
        tvEstadoEquipoPoste = (TextView)rootView.findViewById(R.id.textViewEstadoEquipoPoste);
//
//        if (sTipoEquipo.contains("KP-PE050")) //Poste SOS
//        {
//            rootView = inflater.inflate(R.layout.tab_estado_equipo, container, false);
//            tvEstadoEquipoPoste = (TextView)rootView.findViewById(R.id.textViewEstadoEquipoPoste);
//        }
//        else
//            rootView = inflater.inflate(R.layout.tab_estado, container, false);

        return rootView;
    }
    public static void SetEstadoEquipo(String sEstado)
    {
        tvEstadoEquipoPoste.setText(sEstado);
    }

}
