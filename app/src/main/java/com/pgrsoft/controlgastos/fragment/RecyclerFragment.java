package com.pgrsoft.controlgastos.fragment;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.adaptador.ListAdapters;
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

        final View myView = inflater.inflate(R.layout.fragment_recycler_listado, container, false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this.getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);

        final MovimientoServices movimientoServices = new MovimientoServicesImpl(this.getActivity());
        final List<Movimiento> movimientos = movimientoServices.getAll();

        final ListAdapters listAdapters = new ListAdapters(movimientos, this);

        recyclerView.setAdapter(listAdapters);

        // esta parte te ayuda
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                final int position = viewHolder.getAdapterPosition();
                final Movimiento movimiento = movimientos.get(position);

                movimientos.remove(position);
                listAdapters.notifyDataSetChanged();
                boolean deleting = movimientoServices.delete(movimiento.getCodigo());
                Snackbar snackbar = Snackbar.make(recyclerView,"Item was removed from the list.",
                        Snackbar.LENGTH_LONG);

                snackbar.setAction("UNDO", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        movimientos.add(position, movimiento);
                        recyclerView.scrollToPosition(position);
                        movimientoServices.create(movimiento);
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        return myView;
    }


    @Override
    public void myClickList(int position) {

        //ProductoServices productoServices = new ProductoServicesImpl(this.getActivity());
        MovimientoServices movimientoServices = new MovimientoServicesImpl(this.getActivity());

        List<Movimiento> movimientos = movimientoServices.getAll();

        //Producto producto = productos.get(position);

        Movimiento movimiento = movimientos.get(position);

        /*Dialog dialog = new Dialog(this.getActivity());
        dialog.setContentView(R.layout.fragment_listado_detalle);

        TextView textNombre = (TextView) dialog.findViewById(R.id.idDetalleNombre);
        TextView textDescripcion = (TextView) dialog.findViewById(R.id.idDetalleDescripcion);
        TextView textImporte = (TextView) dialog.findViewById(R.id.idImporte);
        TextView textFecha = (TextView) dialog.findViewById(R.id.idFecha);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.idImageView);

        Log.d("***", "ENTRA EN ESTA FUNCION: ");
        Button btnPhoto = (Button) dialog.findViewById(R.id.idBtnCamera);
        Button btnSave = (Button) dialog.findViewById(R.id.idBtnSave);

        textNombre.setText(movimiento.getProducto().getNombre());
        textDescripcion.setText(movimiento.getDescripcion());
        textImporte.setText(String.valueOf(movimiento.getImporte()));
        textFecha.setText(String.valueOf(movimiento.getFecha()));

        getDrawableImage(movimiento.getProducto().getImagen(), imageView);
        btnPhoto.setOnClickListener(this);
        btnSave.setOnClickListener(this);


        dialog.show();*/

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
