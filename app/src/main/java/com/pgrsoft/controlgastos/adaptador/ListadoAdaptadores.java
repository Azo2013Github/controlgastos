package com.pgrsoft.controlgastos.adaptador;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pgrsoft.controlgastos.model.Movimiento;

import java.util.List;

public class ListadoAdaptadores extends RecyclerView.Adapter {

    List<Movimiento> movimientos;
    public ListadoAdaptadores(List<Movimiento> movimientos){

        this.movimientos = movimientos;

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
        return 0;
    }
}
