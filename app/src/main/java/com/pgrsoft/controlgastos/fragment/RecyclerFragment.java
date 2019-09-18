package com.pgrsoft.controlgastos.fragment;


import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.adaptador.ListAdapters;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment implements ListAdapters.MyListListener{

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

        ListAdapters listAdapters = new ListAdapters(movimientos, this);

        recyclerView.setAdapter(listAdapters);

        return myView;
    }


    @Override
    public void myClickList(int position) {

        //productoServices = new ProductoServicesImpl(this.getActivity());
        MovimientoServices movimientoServices = new MovimientoServicesImpl(this.getActivity());

        //productos = productoServices.getAll();
        List<Movimiento> movimientos = movimientoServices.getAll();

        //Producto producto = productos.get(i);

        Movimiento movimiento = movimientos.get(position);

        Bundle bundle = new Bundle();

        //bundle.putSerializable("PRODUCTOS", producto);
        bundle.putSerializable("MOVIMIENTOS", movimiento);

        ListadoDetalleFragment fragment = new ListadoDetalleFragment();

        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.destino, fragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

    }

}
