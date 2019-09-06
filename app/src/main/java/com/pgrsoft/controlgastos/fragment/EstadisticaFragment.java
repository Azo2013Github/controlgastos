package com.pgrsoft.controlgastos.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

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

    private EditText editDateInicial;
    private EditText editDateFinal;
    private Button btnEstatitic;
    private TextView textDateStart;
    private TextView textDateEnd;

    static int mYearIni;
    static int mMonthIni;
    static int mDayIni;
    private int sYearIni;
    private int sMonthIni;
    private int sDayIni;

    private Calendar calendar;
    private MovimientoServices movimientoServices;
    private List<Movimiento> movimientos;


    public EstadisticaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_estadistica, container, false);
        editDateInicial = (EditText) myView.findViewById(R.id.idEditDateStart);
        editDateFinal = (EditText) myView.findViewById(R.id.idEditDateEnd);
        textDateStart = (TextView) myView.findViewById(R.id.idTextDateStart);
        textDateEnd = (TextView) myView.findViewById(R.id.idTextDateEnd);

        btnEstatitic = (Button) myView.findViewById(R.id.idBtnEstatistic);

        /*Cal = Calendar.getInstance();
        sMonthIni = Cal.get(Calendar.MONTH);
        sDayIni = Cal.get(Calendar.DAY_OF_MONTH);
        sYearIni = Cal.get(Calendar.YEAR);*/

        movimientoServices = new MovimientoServicesImpl(this.getActivity());
        btnEstatitic.setOnClickListener(this);

        editDateInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        editDateInicial.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        editDateFinal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        editDateFinal.setText(mDay + "/" + (mMonth-1) + "/" + mYear);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

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
            case R.id.idBtnEstatistic:
                //showDialog();
                if (editDateInicial.getText().toString().equals("") ||
                        editDateFinal.getText().toString().equals("")){
                    Log.d("***", "Write date in the EditText ");

                }else {


                    Date fechaInicio = null;
                    Date fechaFin = null;
                    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
                    String strFechaInicio = "";
                    String strFechaFin = "";

                    try {
                        fechaInicio = sdf.parse(strFechaInicio);
                        fechaFin = sdf.parse(strFechaFin);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    for (Movimiento movimiento: movimientos){
                        movimientos = movimientoServices.getDateBetween(fechaInicio, fechaFin);
                        textDateStart.setText(String.valueOf(fechaInicio));
                        textDateEnd.setText(String.valueOf(fechaFin));
                    }


                    Log.d("***", "MOVIMIENTOS ARRAY: " + movimientos.toString());
                }



                break;
            /*case R.id.idFechaInicial:
                Cal = Calendar.getInstance();
                int day = Cal.get(Calendar.DAY_OF_YEAR);
                int month = Cal.get(Calendar.DAY_OF_MONTH - 1);
                int year = Cal.get(Calendar.DATE);
                showDialog();
                break;*/
        }

    }

    /*dateTextView = (EditText) view.findViewById(R.id.idFechaNacimiento);

        dateTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        dateTextView.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });*/
}
