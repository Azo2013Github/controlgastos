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
import java.util.ArrayList;
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

        movimientoServices = new MovimientoServicesImpl(this.getActivity());
        btnEstatitic.setOnClickListener(this);

        editDateInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        editDateFinal.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                    }
                }, year, month, day);

                Log.d("***", "DatePicker: " + year +" "+ month +" " +day);
                datePickerDialog.show();

            }
        });

        return myView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.idBtnEstatistic:

                if (editDateInicial.getText().toString().equals("") || editDateFinal.getText().toString().equals("")){
                    Log.d("***", "Write date in the EditText ");
                }else {
                    Date dateIni = new Date();
                    Date dateFin = new Date();

                    String strFechaInicio = editDateInicial.getText().toString();
                    String strFechaFin = editDateFinal.getText().toString();
                    Log.d("***", strFechaFin + " " +strFechaInicio);
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
                        dateIni = sdf.parse(strFechaInicio);
                        dateFin = sdf.parse(strFechaFin);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    movimientos = movimientoServices.getDateBetween(dateIni, dateFin);

                    Log.d("***", "MOVIMIENTOS ARRAY: " + movimientos.toString());
                    for (int i = 0; i < movimientos.size(); i++){
                        Movimiento movimiento = movimientos.get(i);
                        textDateStart.setText(String.valueOf(dateIni));
                        textDateEnd.setText(String.valueOf(dateFin));
                    }

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


}
