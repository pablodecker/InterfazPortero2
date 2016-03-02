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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();
        String sInfo = myEquipoCAPE.getTipoEquipo();// + "\r\n" + c.getString(2);
        if (sInfo.contains("KP-PE015")){
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


            String[] listaNumeros = {"1","2","3","4","5"};
            ArrayAdapter adapterSec = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaNumeros);

            Spinner spSecLlamada1 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_1);
            spSecLlamada1.setAdapter(adapterSec);

            Spinner spSecLlamada2 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_2);
            spSecLlamada2.setAdapter(adapterSec);

            Spinner spSecLlamada3 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_3);
            spSecLlamada3.setAdapter(adapterSec);

            Spinner spSecLlamada4 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_4);
            spSecLlamada4.setAdapter(adapterSec);

            Spinner spSecLlamada5 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_5);
            spSecLlamada5.setAdapter(adapterSec);

            Spinner spSecAlarma1 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_1);
            spSecAlarma1.setAdapter(adapterSec);

            Spinner spSecAlarma2 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_2);
            spSecAlarma2.setAdapter(adapterSec);

            Spinner spSecAlarma3 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_3);
            spSecAlarma3.setAdapter(adapterSec);

            Spinner spSecAlarma4 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_4);
            spSecAlarma4.setAdapter(adapterSec);

            Spinner spSecAlarma5 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_5);
            spSecAlarma5.setAdapter(adapterSec

            );



        }
        else if(sInfo.contains("KP-AL911"))
            rootView = inflater.inflate(R.layout.tab_config2_alarma_gprs, container, false);
        else
            rootView = inflater.inflate(R.layout.tab_config2_alarma_gprs, container, false);
        return rootView;
    }
}
