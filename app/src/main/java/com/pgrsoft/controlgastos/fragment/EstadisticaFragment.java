package com.pgrsoft.controlgastos.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstadisticaFragment extends Fragment implements View.OnClickListener{

    private EditText editFechaInicial;
    private EditText editFechaFinal;

    static int mYearIni;
    static int mMonthIni;
    static int mDayIni;
    private int sYearIni;
    private int sMonthIni;
    private int sDayIni;

    private Calendar Cal;
    private MovimientoServices movimientoServices;


    public EstadisticaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_estadistica, container, false);
        editFechaInicial = (EditText) myView.findViewById(R.id.idFechaInicial);
        editFechaFinal = (EditText) myView.findViewById(R.id.idFechaFinal);

        /*Cal = Calendar.getInstance();
        sMonthIni = Cal.get(Calendar.MONTH);
        sDayIni = Cal.get(Calendar.DAY_OF_MONTH);
        sYearIni = Cal.get(Calendar.YEAR);*/

        movimientoServices = new MovimientoServicesImpl(this.getActivity());

        editFechaInicial.setOnClickListener(this);
        editFechaFinal.setOnClickListener(this);
        return myView;
    }

    /*protected Dialog showDialog() {
        DatePickerDialog mDataPicker =
                new DatePickerDialog(this.getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dateOfMonth) {
                mYearIni = year;
                mMonthIni = monthOfYear;
                mDayIni = dateOfMonth;
            }

        }, mYearIni, mMonthIni, mDayIni);
        mDataPicker.updateDate(2019, 9, 06);
        mDataPicker.show();

        return null;
    }*/


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.idFechaInicial:
                //showDialog();

                Log.d("***", "ENTRADO EDITTEXT: ");
                String strFechaInicio = "10/12/1990";
                String strFechaFin = "5/9/2019";
                Date fechaInicio = null;
                Date fechaFin = null;
                SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm");

                try {
                    fechaInicio = sdf.parse(strFechaInicio);
                    fechaFin = sdf.parse(strFechaFin);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                List<Movimiento> movimientos = movimientoServices.getDateBetween(fechaInicio, fechaFin);



                Log.d("***", "MOVIMIENTOS ARRAY: " + movimientos.toString());
                break;
            case R.id.idFechaFinal:
                //showDialog();
                break;
        }

    }
}
