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
import com.pgrsoft.controlgastos.adaptador.ProductosAdaptador;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductoListadoFragment extends Fragment implements ListView.OnItemClickListener{

    private ListView lista;

    private List<Producto> productos;
    private ProductoServices productoServices;


    public ProductoListadoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_listado, container, false);

        lista = miVista.findViewById(R.id.idMiLista);

        ProductosAdaptador productosAdaptador = new ProductosAdaptador(miVista.getContext());

        lista.setAdapter(productosAdaptador);

        lista.setOnItemClickListener(this);

        return miVista;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        productoServices = new ProductoServicesImpl(this.getActivity());
        MovimientoServices movimientoServices = new MovimientoServicesImpl(this.getActivity());

        productos = productoServices.getAll();
        List<Movimiento> movimientos = movimientoServices.getAll();

        Producto producto = productos.get(i);

        Movimiento movimiento = movimientos.get(i);

        Bundle bundle = new Bundle();

        bundle.putSerializable("PRODUCTOS", producto);
        bundle.putSerializable("MOVIMIENTOS", movimiento);

        Fragment fragment = new ListadoDetalleFragment();

        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.destino, fragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

    }
}
