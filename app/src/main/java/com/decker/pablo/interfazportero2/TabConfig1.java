package com.decker.pablo.interfazportero2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pablo on 03/01/2016.
 */
public class TabConfig1 extends Fragment {
    private static EditText etTe1,etTe2,etTe3,etTe4,etTe5, etTiempoCom, etTiempoReporte, etEmpresa, etIDPoste, etTiempoRepe, etNombreEquipoAlarma;
    private static Switch swHab;
    private static Spinner spSecLlamada1,spSecLlamada2,spSecLlamada3,spSecLlamada4,spSecLlamada5,
                            spSecAlarma1,spSecAlarma2,spSecAlarma3,spSecAlarma4,spSecAlarma5, spVolPoste, spMicPoste;
    private static TextView tvSgn, tvBat;
    private static LinearLayout myLayoutConfig1Portero;
    private static String sTipoEquipo;
    EquipoCAPE myEquipoCAPE;


    private static View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Calling Application class (see application tag in AndroidManifest.xml)
        myEquipoCAPE = (EquipoCAPE)getActivity().getApplicationContext();
        sTipoEquipo = myEquipoCAPE.getTipoEquipo();

        if (sTipoEquipo.contains("KP-PE050"))
        {  //Poste SOS
            rootView = inflater.inflate(R.layout.tab_config1_poste_sos, container, false);
            etTe1 = (EditText) rootView.findViewById(R.id.etTelefono1);
            etTe2 = (EditText) rootView.findViewById(R.id.etTelefono2);
            etTe3 = (EditText) rootView.findViewById(R.id.etTelefono3);
            etTe4 = (EditText) rootView.findViewById(R.id.etTelefono4);
            etTiempoCom = (EditText) rootView.findViewById(R.id.etTiempoComunicacion);
            etTiempoReporte = (EditText) rootView.findViewById(R.id.etTiempoReporte);
            etEmpresa = (EditText) rootView.findViewById(R.id.etEmpresaPoste);
            etIDPoste = (EditText) rootView.findViewById(R.id.etIDPoste);
            swHab = (Switch) rootView.findViewById(R.id.switchHabilitacion);

            tvSgn = (TextView)rootView.findViewById(R.id.textViewSgn);
            tvBat = (TextView)rootView.findViewById(R.id.textViewBat);

            String[] listaNumerosVol = {"0","1","2","3","4","5","6","7"};
            ArrayAdapter adapterSecVol = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaNumerosVol);

            spVolPoste = (Spinner)rootView.findViewById(R.id.spinner_vol_poste);
            spVolPoste.setAdapter(adapterSecVol);

            String[] listaNumerosMic = {"0","1","2","3","4","5","6","7","8","9","10"};
            ArrayAdapter adapterSecMic = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaNumerosMic);

            spMicPoste = (Spinner)rootView.findViewById(R.id.spinner_mic_poste);
            spMicPoste.setAdapter(adapterSecMic);
        }
        //INTERFAZ PORTERO
        else if (sTipoEquipo.contains("KP-PE015"))
        {
            rootView = inflater.inflate(R.layout.tab_config1_interfaz_portero, container, false);


            etTe1 = (EditText) rootView.findViewById(R.id.etTelefono1);
            etTe2 = (EditText) rootView.findViewById(R.id.etTelefono2);
            etTe3 = (EditText) rootView.findViewById(R.id.etTelefono3);
            etTe4 = (EditText) rootView.findViewById(R.id.etTelefono4);
            etTe5 = (EditText) rootView.findViewById(R.id.etTelefono5);
            swHab = (Switch) rootView.findViewById(R.id.switchHabilitacion);

            String[] listaNumeros = {"0","1","2","3","4","5"};
            ArrayAdapter adapterSec = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item, listaNumeros);

            spSecLlamada1 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_1);
            spSecLlamada1.setAdapter(adapterSec);

            spSecLlamada2 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_2);
            spSecLlamada2.setAdapter(adapterSec);

            spSecLlamada3 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_3);
            spSecLlamada3.setAdapter(adapterSec);

            spSecLlamada4 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_4);
            spSecLlamada4.setAdapter(adapterSec);

            spSecLlamada5 = (Spinner)rootView.findViewById(R.id.spinner_sec_llamada_5);
            spSecLlamada5.setAdapter(adapterSec);

            spSecAlarma1 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_1);
            spSecAlarma1.setAdapter(adapterSec);

            spSecAlarma2 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_2);
            spSecAlarma2.setAdapter(adapterSec);

            spSecAlarma3 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_3);
            spSecAlarma3.setAdapter(adapterSec);

            spSecAlarma4 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_4);
            spSecAlarma4.setAdapter(adapterSec);

            spSecAlarma5 = (Spinner)rootView.findViewById(R.id.spinner_sec_alarma_5);
            spSecAlarma5.setAdapter(adapterSec);

        }
        //Alarma
        else
        {
            rootView = inflater.inflate(R.layout.tab_config1_alarma, container, false);
            etNombreEquipoAlarma = (EditText) rootView.findViewById(R.id.etNombreEquipoAlarma);
            etTe1 = (EditText) rootView.findViewById(R.id.etTelefono1);
            etTe2 = (EditText) rootView.findViewById(R.id.etTelefono2);
            etTe3 = (EditText) rootView.findViewById(R.id.etTelefono3);
            etTe4 = (EditText) rootView.findViewById(R.id.etTelefono4);
            etTe5 = (EditText) rootView.findViewById(R.id.etTelefono5);
            etTiempoRepe = (EditText) rootView.findViewById(R.id.etTiempoRepeticion);
            swHab = (Switch) rootView.findViewById(R.id.switchHabilitacion);
            tvSgn = (TextView)rootView.findViewById(R.id.textViewSgn);
            tvBat = (TextView)rootView.findViewById(R.id.textViewBat);
        }

        if (myEquipoCAPE.getRecibioConfig1() == false)
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Button b = (Button) rootView.findViewById(R.id.buttonConfigurarConfig1);
        b.setOnClickListener(new View.OnClickListener()
        {
            //Boton configurar
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String phoneNo = myEquipoCAPE.getNumTel();
                String sms = "";
                if (sTipoEquipo.contains("KP-PE050"))//Poste SOS
                {
                    String sHab = " Hab:No";
                    if(swHab.isChecked())
                        sHab = " Hab:Si";
                    sms = "Config:" + sHab +
                            " Id:" + etIDPoste.getText().toString() +
                            " emp:" + etEmpresa.getText() +
                            " Te1:" + etTe1.getText().toString() +
                            " Te2:" + etTe2.getText().toString() +
                            " Te3:" + etTe3.getText().toString() +
                            " TeR:" + etTe4.getText().toString() +
                            " Vol:" + spVolPoste.getSelectedItem().toString() +
                            " Mic:" + spMicPoste.getSelectedItem().toString() +
                            " TCom:" + etTiempoCom.getText() +
                            " Trep:" + etTiempoReporte.getText();
                    myEquipoCAPE.enviar_sms(phoneNo, sms);
                }
                else if (sTipoEquipo.contains("KP-AL911"))//ALARMA
                {
                    String sHab = " Hab:No";
                    if(swHab.isChecked())
                        sHab = " Hab:Si";
                    sms = "Config:" + sHab +
                            " Nombre:" + etNombreEquipoAlarma.getText().toString() +
                            " Te1:" + etTe1.getText().toString() +
                            " Te2:" + etTe2.getText().toString() +
                            " Te3:" + etTe3.getText().toString() +
                            " Te4:" + etTe4.getText().toString() +
                            " Te5:" + etTe5.getText().toString() +
                            " Trepe:" + etTiempoReporte.getText().toString();
                    myEquipoCAPE.enviar_sms(phoneNo, sms);
                }
                else if (sTipoEquipo.contains("KP-PE015"))//INTERFAZ PORTERO
                {
                    String sHab = " Hab:No";
                    if(swHab.isChecked())
                        sHab = " Hab:Si";
                    sms = "Config:" + sHab +
                            " Te1:" + etTe1.getText().toString() +
                            " Te2:" + etTe2.getText().toString() +
                            " Te3:" + etTe3.getText().toString() +
                            " Te4:" + etTe4.getText().toString() +
                            " Te5:" + etTe5.getText().toString() + "\r\n";
                    sms += "secll:";
                    sms += spSecLlamada1.getSelectedItem().toString() + ",";
                    sms += spSecLlamada2.getSelectedItem().toString() + ",";
                    sms += spSecLlamada3.getSelectedItem().toString() + ",";
                    sms += spSecLlamada4.getSelectedItem().toString() + ",";
                    sms += spSecLlamada5.getSelectedItem().toString() + "\r\n";

                    sms += "secal:";
                    sms += spSecAlarma1.getSelectedItem().toString() + ",";
                    sms += spSecAlarma2.getSelectedItem().toString() + ",";
                    sms += spSecAlarma3.getSelectedItem().toString() + ",";
                    sms += spSecAlarma4.getSelectedItem().toString() + ",";
                    sms += spSecAlarma5.getSelectedItem().toString();
                    myEquipoCAPE.enviar_sms(phoneNo, sms);
                }
                setViewAndChildrenEnabled(rootView, false);
            }

        });
    }

    public static void SetControlesInterfazPortero(boolean bSwitchHab, String sTe1, String sTe2, String sTe3, String sTe4, String sTe5,
                                                   int iSecLlamada1, int iSecLlamada2, int iSecLlamada3, int iSecLlamada4, int iSecLlamada5,
                                                   int iSecAlarma1, int iSecAlarma2, int iSecAlarma3, int iSecAlarma4, int iSecAlarma5)
    {
        setViewAndChildrenEnabled(rootView, true);

        swHab.setChecked(bSwitchHab);
        etTe1.setText(sTe1); etTe2.setText(sTe2); etTe3.setText(sTe3); etTe4.setText(sTe4); etTe5.setText(sTe5);
        spSecLlamada1.setSelection(iSecLlamada1); spSecLlamada2.setSelection(iSecLlamada2); spSecLlamada3.setSelection(iSecLlamada3); spSecLlamada4.setSelection(iSecLlamada4); spSecLlamada5.setSelection(iSecLlamada5);
        spSecAlarma1.setSelection(iSecAlarma1); spSecAlarma2.setSelection(iSecAlarma2); spSecAlarma3.setSelection(iSecAlarma3); spSecAlarma4.setSelection(iSecAlarma4); spSecAlarma5.setSelection(iSecAlarma5);
    }
    public static void SetControlesAlarma(boolean bSwitchHab, String sTe1, String sTe2, String sTe3, String sTe4, String sTe5,String sSgn,
                                          String sBat, String sTiempoRepe , String sNombreEquipo )
    {
        setViewAndChildrenEnabled(rootView, true);

        swHab.setChecked(bSwitchHab);
        etTe1.setText(sTe1); etTe2.setText(sTe2); etTe3.setText(sTe3); etTe4.setText(sTe4); etTe5.setText(sTe5);
        etTiempoRepe.setText(sTiempoRepe);
        etNombreEquipoAlarma.setText(sNombreEquipo);

        if (sTipoEquipo.contains("KP-TM005"))
        {
            tvSgn.setText("Señal: " + sSgn);
            tvBat.setText("Bat: " + sBat + "v");
        }
        else
        {
            tvSgn.setVisibility(View.INVISIBLE);
            tvBat.setVisibility(View.INVISIBLE);
        }
    }
    public static void SetControlesPosteSOS(boolean bSwitchHab, String sTe1, String sTe2, String sTe3, String sTeR, int iMic, int iVol,
                                            String sTcom, String sTRep, String sSgn, String sBat, String sEmpresa, String sID)
    {

        setViewAndChildrenEnabled(rootView, true);

        swHab.setChecked(bSwitchHab);
        etTe1.setText(sTe1); etTe2.setText(sTe2); etTe3.setText(sTe3); etTe4.setText(sTeR);
        etTiempoCom.setText(sTcom);
        etTiempoReporte.setText(sTRep);
        tvSgn.setText("Señal: " + sSgn);
        tvBat.setText("Bat: " + sBat + "v");
        spMicPoste.setSelection(iMic);
        spVolPoste.setSelection(iVol);
        etEmpresa.setText(sEmpresa);
        etIDPoste.setText(sID);
    }
}
