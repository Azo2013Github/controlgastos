package com.pgrsoft.controlgastos.fragment;


import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.adaptador.MovimientosAdaptador;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovimientoListadoFragment extends Fragment {

    private ListView lista;


    public MovimientoListadoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movimiento_listado, container, false);

        lista = (ListView) view.findViewById(R.id.idMiLista);

        MovimientosAdaptador movimientosAdaptador = new MovimientosAdaptador(view.getContext());

        lista.setAdapter(movimientosAdaptador);

        return view;
    }

}
