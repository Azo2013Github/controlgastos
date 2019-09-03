package com.pgrsoft.controlgastos.fragment;


import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.adaptador.MovimientosAdaptador;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovimientoListadoFragment extends Fragment implements ListView.OnItemClickListener{

    private ListView lista;
    private MovimientoServices movimientoServices;
    private List<Movimiento> movimientos;

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

        lista.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        /*movimientoServices = new MovimientoServicesImpl(this.getActivity());

        movimientos = movimientoServices.getAll();

        Movimiento movimiento = movimientos.get(i);

        Bundle bundle = new Bundle();

        bundle.putSerializable("MOVIMIENTOS", movimiento);

        Fragment fragment = new ListadoDetalleFragment();

        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.destino, fragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();*/
    }
}
