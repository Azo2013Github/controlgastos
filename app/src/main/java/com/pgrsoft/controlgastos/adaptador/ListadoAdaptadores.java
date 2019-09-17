package com.pgrsoft.controlgastos.adaptador;


import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;

import java.util.List;

public class ListadoAdaptadores extends RecyclerView.Adapter {

    private List<Movimiento> movimientos;
    private MovimientoServices movimientoServices;
    private Context context;


    public ListadoAdaptadores(Context context){

        movimientoServices = new MovimientoServicesImpl(this.context);
        this.movimientos = movimientoServices.getAll();


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return movimientos.size();
    }
}
