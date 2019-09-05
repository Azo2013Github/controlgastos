package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.fragment.ListadoDetalleFragment;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;

import java.util.List;

public class DiagramaActivity extends AppCompatActivity {

    private TextView dateToDay;
    private TextView dateToUNWeek;
    private List<Movimiento> movimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagrama);

        dateToDay = (TextView) findViewById(R.id.idDateToDay);
        dateToUNWeek = (TextView) findViewById(R.id.idDateToWeek);

        Bundle bundle = getIntent().getExtras();

        Movimiento movimiento = (Movimiento) bundle.getSerializable("MOVIMIENTOS");

        dateToDay.setText(String.valueOf(movimiento.getFecha()));
        dateToUNWeek.setText(String.valueOf(movimiento.getFecha()));




    }
}
