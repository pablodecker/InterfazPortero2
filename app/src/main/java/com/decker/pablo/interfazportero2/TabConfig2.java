package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabConfig2 extends Fragment {
    EquipoCAPE myEquipoCAPE;
    View rootView;
    private static Spinner spAudio1,spAudio2,spAudio3,spAudio4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();
        String sInfo = myEquipoCAPE.getTipoEquipo();// + "\r\n" + c.getString(2);
        if (sInfo.contains("KP-PE015")){ //INTERFAZ PORTERO
            rootView = inflater.inflate(R.layout.tab_config2_portero_cfgadmin, container, false);

            Spinner spTipoEntrada1;
            String[] listaEntradas1 = {"A","H","P"};
            spTipoEntrada1 = (Spinner)rootView.findViewById(R.id.spinner_tipo_en1);
            ArrayAdapter adapter1 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaEntradas1);
            spTipoEntrada1.setAdapter(adapter1);

            Spinner spTipoEntrada2;
            String[] listaEntradas2 = {"A","H"};
            spTipoEntrada2 = (Spinner)rootView.findViewById(R.id.spinner_tipo_en2);
            ArrayAdapter adapter2 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaEntradas2);
            spTipoEntrada2.setAdapter(adapter2);

            Spinner spTipoEntrada3;
            String[] listaEntradas3 = {"P"};
            spTipoEntrada3 = (Spinner)rootView.findViewById(R.id.spinner_tipo_en3);
            ArrayAdapter adapter3 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaEntradas3);
            spTipoEntrada3.setAdapter(adapter3);

            String[] listaNumeros = {"0","1","2","3","4","5","6","7","8","9"};
            ArrayAdapter adapterSec = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaNumeros);
            spAudio1 = (Spinner)rootView.findViewById(R.id.spinner_audio_1);
            spAudio1.setAdapter(adapterSec);
            spAudio2 = (Spinner)rootView.findViewById(R.id.spinner_audio_2);
            spAudio2.setAdapter(adapterSec);
            spAudio3 = (Spinner)rootView.findViewById(R.id.spinner_audio_3);
            spAudio3.setAdapter(adapterSec);
            spAudio4 = (Spinner)rootView.findViewById(R.id.spinner_audio_4);
            spAudio4.setAdapter(adapterSec);

        }
        else if(sInfo.contains("KP-AL911"))
            rootView = inflater.inflate(R.layout.tab_config2_alarma_gprs, container, false);
        else
            rootView = inflater.inflate(R.layout.tab_config2_alarma_gprs, container, false);
        return rootView;
    }
}
