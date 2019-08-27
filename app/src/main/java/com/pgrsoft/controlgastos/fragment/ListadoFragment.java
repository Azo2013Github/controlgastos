package com.pgrsoft.controlgastos.fragment;


import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.adaptador.ProductosAdaptador;
import com.pgrsoft.controlgastos.model.Producto;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoFragment extends Fragment {


    public ListadoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_listado, container, false);

        ListView lista = miVista.findViewById(R.id.idMiLista);



        ProductosAdaptador productosAdaptador = new ProductosAdaptador(miVista.getContext());

        lista.setAdapter(productosAdaptador);

        return miVista;
    }


}
