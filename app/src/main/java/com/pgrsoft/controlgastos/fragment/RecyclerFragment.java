package com.pgrsoft.controlgastos.fragment;


import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.adaptador.ListadoAdaptadores;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment  {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_recycler_listado, container, false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MovimientoServices movimientoServices = new MovimientoServicesImpl(this.getActivity());
        List<Movimiento> movimientos = movimientoServices.getAll();

        ListadoAdaptadores listadoAdaptadores = new ListadoAdaptadores(movimientos);

        recyclerView.setAdapter(listadoAdaptadores);

        return myView;
    }


}
