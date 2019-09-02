package com.pgrsoft.controlgastos.adaptador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MovimientosAdaptador extends BaseAdapter {

    private Context contexto;
    private List<Movimiento> movimientos;
    private MovimientoServices movimientoServices;

    private static final SimpleDateFormat SDF_FECHA = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat SDF_HORA = new SimpleDateFormat("HH:mm");

    private LayoutInflater inflater;

    public MovimientosAdaptador(Context contexto){

        this.contexto = contexto;

        this.inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        movimientoServices = new MovimientoServicesImpl(this.contexto);
        movimientos = movimientoServices.getAll();

    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {

        View miVista = inflater.inflate(R.layout.movimiento_row_model, null);

        TextView textImporte = (TextView) miVista.findViewById(R.id.idImporte);
        TextView textSaldo = (TextView) miVista.findViewById(R.id.idSaldo);
        TextView textFecha = (TextView) miVista.findViewById(R.id.idFecha);

        Movimiento movimiento = movimientos.get(posicion);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

//      textView is the TextView view that should display it
        textFecha.setText(currentDateTimeString);

        //textFecha.setText(SDF_FECHA.format(movimiento.getFecha()) + " " + SDF_HORA.format(movimiento.getFecha()));
        textImporte.setText(String.valueOf(movimiento.getImporte()));
        textSaldo.setText(String.valueOf(movimiento.getSaldo()));

        return miVista;
    }

    @Override
    public int getCount() {

        return movimientos.size();
    }

    @Override
    public Object getItem(int i) {

        return movimientos.get(i);
    }

    @Override
    public long getItemId(int i) {

        return movimientos.get(i).getCodigo();
    }


}
