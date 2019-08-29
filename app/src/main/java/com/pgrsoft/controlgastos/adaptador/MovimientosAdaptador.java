package com.pgrsoft.controlgastos.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;

import java.util.List;

public class MovimientosAdaptador extends BaseAdapter {

    private Context contexto;
    private List<Movimiento> movimientos;
    private MovimientoServices movimientoServices;

    private LayoutInflater inflater;

    public MovimientosAdaptador(Context contexto){

        this.contexto = contexto;

        this.inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        movimientoServices = new MovimientoServicesImpl(this.contexto);

        movimientos = movimientoServices.getAll();

    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View miVista = inflater.inflate(R.layout.producto_row_model, null);




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
