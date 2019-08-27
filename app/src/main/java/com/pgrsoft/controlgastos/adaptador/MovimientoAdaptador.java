package com.pgrsoft.controlgastos.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;

import java.util.List;

public class MovimientoAdaptador extends BaseAdapter {

    private Context contexto;
    private List<Movimiento> movimientos;

    private LayoutInflater inflater;

    public MovimientoAdaptador (Context contexto, List<Movimiento> movimientos){

        this.contexto = contexto;
        this.movimientos = movimientos;

        this.inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View miVista = inflater.inflate(R.layout.listado_row_model, null);




        return miVista;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
