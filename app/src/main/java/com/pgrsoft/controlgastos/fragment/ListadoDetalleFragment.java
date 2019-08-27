package com.pgrsoft.controlgastos.fragment;


import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoDetalleFragment extends Fragment {


    private List<Producto> productos;
    private ProductoServices productoServices;
    public ListadoDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_listado_detalle, container, false);

        TextView textNombre = (TextView) miVista.findViewById(R.id.idDetalleNombre);
        TextView textDescripcion = (TextView) miVista.findViewById(R.id.idDetalleDescripcion);

        Bundle bundle = getArguments();

        Producto producto = (Producto) bundle.getSerializable("DATOS");

        textNombre.setText(producto.getNombre());
        textDescripcion.setText(producto.getDescripcion());

        return miVista;
    }

}
