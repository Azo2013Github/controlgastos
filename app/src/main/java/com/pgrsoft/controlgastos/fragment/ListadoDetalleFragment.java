package com.pgrsoft.controlgastos.fragment;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.activities.DiagramaActivity;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoDetalleFragment extends Fragment {

    private Button btnEstadisticas;
    private MovimientoServices movimientoServices;

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
        TextView textImporte = (TextView) miVista.findViewById(R.id.idImporte);
        TextView textFecha = (TextView) miVista.findViewById(R.id.idFecha);

        Bundle bundle = getArguments();
        Producto producto = (Producto) bundle.getSerializable("PRODUCTOS");
        Movimiento movimiento = (Movimiento) bundle.getSerializable("MOVIMIENTOS");

        textNombre.setText(producto.getNombre());
        textDescripcion.setText(movimiento.getDescripcion());
        textImporte.setText(String.valueOf(movimiento.getImporte()));
        textFecha.setText(String.valueOf(movimiento.getFecha()));

        btnEstadisticas = (Button) miVista.findViewById(R.id.idBtnEstadisticas);

        btnEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(view.getContext().getApplicationContext(), DiagramaActivity.class);

                view.getContext().startActivity(intent);*/
                Fragment fragment = new EstadisticaFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

            }
        });


        return miVista;
    }


    /*@Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), DiagramaActivity.class);

        getActivity().startActivity(intent);

    }*/


}
