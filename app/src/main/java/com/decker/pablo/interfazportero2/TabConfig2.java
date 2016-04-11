package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabConfig2 extends Fragment {
    EquipoCAPE myEquipoCAPE;
    private static View rootView;
    private static Spinner spVolTel, spMicTel, spVolFrente, spMicFrente,spTipoEntrada1, spTipoEntrada2, spTipoEntrada3;
    private static CheckBox chkHabEn1,chkHabEn2,chkHabEn3, chkLlamar1,chkLlamar2,chkLlamar3,chkSMS1,chkSMS2,chkSMS3;
    private static EditText etSMS1,etSMS2,etSMS3, etIP,etPuerto, etTReporte;
    String sTipoEquipo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();
        String sInfo = myEquipoCAPE.getTipoEquipo();// + "\r\n" + c.getString(2);
        if (sInfo.contains("KP-PE015"))//INTERFAZ PORTERO
        {
            rootView = inflater.inflate(R.layout.tab_config2_portero_cfgadmin, container, false);

            String[] listaEntradas1 = {"A"};
            spTipoEntrada1 = (Spinner)rootView.findViewById(R.id.spinner_tipo_en1);
            ArrayAdapter adapter1 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaEntradas1);
            spTipoEntrada1.setAdapter(adapter1);

            String[] listaEntradas2 = {"A","H"};
            spTipoEntrada2 = (Spinner)rootView.findViewById(R.id.spinner_tipo_en2);
            ArrayAdapter adapter2 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaEntradas2);
            spTipoEntrada2.setAdapter(adapter2);

            String[] listaEntradas3 = {"A","P"};
            spTipoEntrada3 = (Spinner)rootView.findViewById(R.id.spinner_tipo_en3);
            ArrayAdapter adapter3 = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaEntradas3);
            spTipoEntrada3.setAdapter(adapter3);

            String[] listaNumeros = {"0","1","2","3","4","5","6","7","8","9"};
            ArrayAdapter adapterSec = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaNumeros);
            spVolTel = (Spinner)rootView.findViewById(R.id.spinner_vol_telefono);
            spVolTel.setAdapter(adapterSec);
            spMicTel = (Spinner)rootView.findViewById(R.id.spinner_mic_telefono);
            spMicTel.setAdapter(adapterSec);
            spVolFrente = (Spinner)rootView.findViewById(R.id.spinner_vol_frente);
            spVolFrente.setAdapter(adapterSec);
            spMicFrente = (Spinner)rootView.findViewById(R.id.spinner_mic_frente);
            spMicFrente.setAdapter(adapterSec);

            chkHabEn1 = (CheckBox)rootView.findViewById(R.id.checkBoxHabEnt1);
            chkHabEn2 = (CheckBox)rootView.findViewById(R.id.checkBoxHabEnt2);
            chkHabEn3 = (CheckBox)rootView.findViewById(R.id.checkBoxHabEnt3);
            chkLlamar1 = (CheckBox)rootView.findViewById(R.id.checkBoxLlamar1);
            chkLlamar2 = (CheckBox)rootView.findViewById(R.id.checkBoxLlamar2);
            chkLlamar3 = (CheckBox)rootView.findViewById(R.id.checkBoxLlamar3);
            chkSMS1 = (CheckBox)rootView.findViewById(R.id.checkBoxSMS1);
            chkSMS2 = (CheckBox)rootView.findViewById(R.id.checkBoxSMS2);
            chkSMS3 = (CheckBox)rootView.findViewById(R.id.checkBoxSMS3);

            etSMS1 = (EditText)rootView.findViewById(R.id.etTxtSMS1);
            etSMS2 = (EditText)rootView.findViewById(R.id.etTxtSMS2);
            etSMS3 = (EditText)rootView.findViewById(R.id.etTxtSMS3);


            Button b = (Button) rootView.findViewById(R.id.buttonConfigurarConfig2);
            b.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    String phoneNo = myEquipoCAPE.getNumTel();
                    String sms = "";
                    if (sTipoEquipo.contains("KP-PE015"))//INTERFAZ PORTERO
                    {
                        String sHab = " Hab:No";
                        //En1:1
                        sms = "Config:\r\n";
                        sms += " En1:";
                        if (chkHabEn1.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        sms += spTipoEntrada1.getSelectedItem().toString() + ",";
                        if (chkLlamar1.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        if (chkSMS1.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        sms += etSMS1.getText() + "\r\n";

                        sms += " En2:";
                        if (chkHabEn2.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        sms += spTipoEntrada2.getSelectedItem().toString() + ",";
                        if (chkLlamar2.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        if (chkSMS2.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        sms += etSMS2.getText() + "\r\n";

                        sms += " En3:";
                        if (chkHabEn3.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        sms += spTipoEntrada3.getSelectedItem().toString() + ",";
                        if (chkLlamar3.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        if (chkSMS3.isChecked())
                            sms += "1,";
                        else
                            sms += "0,";
                        sms += etSMS3.getText() + "\r\n";

                        sms += "audio:";
                        sms += spVolFrente.getSelectedItem().toString() + ",";
                        sms += spMicFrente.getSelectedItem().toString() + ",";
                        sms += spVolTel.getSelectedItem().toString() + ",";
                        sms += spMicTel.getSelectedItem().toString();

                        myEquipoCAPE.enviar_sms(phoneNo, sms);
                    }
                    else if (sTipoEquipo.contains("KP-AL911"))//ALARMA
                    {

                    }
                }

            });

        }
        else if(sInfo.contains("KP-AL911")) {

            rootView = inflater.inflate(R.layout.tab_config2_alarma_gprs, container, false);
            etIP = (EditText)rootView.findViewById(R.id.etURL);
            etPuerto = (EditText)rootView.findViewById(R.id.etPuerto);
            etTReporte = (EditText)rootView.findViewById(R.id.etTiempoReporte);
        }
        else
        {
            rootView = inflater.inflate(R.layout.tab_config2_alarma_gprs, container, false);
        }
        if (myEquipoCAPE.getRecibioConfig2() == false)
            setViewAndChildrenEnabled(rootView, false);


        return rootView;
    }

    //funcion para habilitar o deshabilitar todos los controles
    private static void setViewAndChildrenEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                setViewAndChildrenEnabled(child, enabled);
            }
        }
    }


    public static void SetControlesInterfazPortero(boolean bHabEn1,boolean bHabEn2,boolean bHabEn3,boolean bLlamar1,boolean bLlamar2,boolean bLlamar3,
                                                   boolean bSMS1,boolean bSMS2,boolean bSMS3,String sTipoEn1,String sTipoEn2,String sTipoEn3,
                                                   String sTxtSMS1,String sTxtSMS2,String sTxtSMS3,int iVolTel,int iMicTel, int iVolFrente, int iMicFrente)
    {

        setViewAndChildrenEnabled(rootView, true);

        chkHabEn1.setChecked(bHabEn1);
        chkHabEn2.setChecked(bHabEn2);
        chkHabEn3.setChecked(bHabEn3);

        chkLlamar1.setChecked(bLlamar1);
        chkLlamar2.setChecked(bLlamar2);
        chkLlamar3.setChecked(bLlamar3);

        chkSMS1.setChecked(bSMS1);
        chkSMS2.setChecked(bSMS2);
        chkSMS3.setChecked(bSMS3);

        if(sTipoEn1.contains("a"))
            spTipoEntrada1.setSelection(0);

        if(sTipoEn2.contains("a"))
            spTipoEntrada2.setSelection(0);
        else if(sTipoEn2.contains("h"))
            spTipoEntrada2.setSelection(1);

        if(sTipoEn3.contains("a"))
            spTipoEntrada3.setSelection(0);
        else if(sTipoEn3.contains("h"))
            spTipoEntrada3.setSelection(1);

        etSMS1.setText(sTxtSMS1);
        etSMS2.setText(sTxtSMS2);
        etSMS3.setText(sTxtSMS3);

        spVolTel.setSelection(iVolTel);
        spMicTel.setSelection(iMicTel);
        spVolFrente.setSelection(iVolFrente);
        spMicFrente.setSelection(iMicFrente);
    }
    public static void SetControlesAlarma(String sIP,String sPuerto,String sTiempoReporte)
    {
        setViewAndChildrenEnabled(rootView, true);
        etIP.setText(sIP);
        etPuerto.setText(sPuerto);
        etTReporte.setText(sTiempoReporte);
    }

}
