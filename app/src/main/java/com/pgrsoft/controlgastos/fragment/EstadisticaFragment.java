package com.pgrsoft.controlgastos.fragment;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;

import java.text.DateFormat;
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
    private BarChart barChart;

    private Calendar calendar;
    private MovimientoServices movimientoServices;
    private List<Movimiento> movimientos;


    public EstadisticaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_estadistica, container, false);
        editDateInicial = (EditText) myView.findViewById(R.id.idEditDateStart);
        editDateFinal = (EditText) myView.findViewById(R.id.idEditDateEnd);

        barChart = (BarChart) myView.findViewById(R.id.idBarChart); //Esta parte no funciona:
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        editDateFinal.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        return myView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.idBtnEstatistic:

                List<BarEntry> barEntries = new ArrayList<>();
                List<String> strEntries = new ArrayList<>();

                if (editDateInicial.getText().toString().equals("") || editDateFinal.getText().toString().equals("")){
                    Log.d("***", "Write date in the EditText ");
                }else {
                    Date dateIni = new Date();
                    Date dateFin = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
                    String strFechaInicio = editDateInicial.getText().toString();
                    String strFechaFin = editDateFinal.getText().toString();

                    Log.d("***Edit", editDateInicial.getText().toString());
                    try {

                        dateIni = sdf.parse(strFechaInicio);
                        dateFin = sdf.parse(strFechaFin);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    movimientos = movimientoServices.getDateBetween(dateIni, dateFin);
                    int i = 0;
                    for (Movimiento movimiento: movimientos){
                        barEntries.add(new BarEntry((float)(movimiento.getImporte()), i));
                        i++;
                        strEntries.add(movimiento.getProducto().getNombre());
                    }

                    BarDataSet barDataset = new BarDataSet(barEntries, "Gastos");
                    BarData barData = new BarData(strEntries, barDataset);
                    barChart.setData(barData);

                    barChart.setDescription("Importe de Gastos por semana: ");
                    barDataset.setColors(ColorTemplate.JOYFUL_COLORS);
                    barDataset.setValueTextColor(Color.BLACK);
                    barDataset.setValueTextSize(18f);
                    barChart.animateY(3000);
                }

                break;
        }

    }



}
