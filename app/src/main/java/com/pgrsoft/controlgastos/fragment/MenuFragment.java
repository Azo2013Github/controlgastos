package com.pgrsoft.controlgastos.fragment;


import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pgrsoft.controlgastos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{


    private Button btnCrear;
    private Button btnListarProducto;
    //private Button btnSalir;
    private Button btnListarMovimiento;

    private Fragment fragment;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View miVista = inflater.inflate(R.layout.fragment_menu, container, false);

        btnCrear = (Button) miVista.findViewById(R.id.idBtnCrear);
        btnListarProducto = (Button) miVista.findViewById(R.id.idBtnProducto);
        //btnSalir = (Button) miVista.findViewById(R.id.idBtnSalir);
        btnListarMovimiento = (Button) miVista.findViewById(R.id.idBtnMovimiento);

        btnCrear.setOnClickListener(this);
        //btnSalir.setOnClickListener(this);
        btnListarProducto.setOnClickListener(this);
        btnListarMovimiento.setOnClickListener(this);

        return miVista;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.idBtnCrear:

                fragment = new FormularioFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;

            case R.id.idBtnProducto:

                fragment = new ProductoListadoFragment();

                fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;

            case R.id.idBtnMovimiento:

                fragment = new MovimientoListadoFragment();

                fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();


                break;

            /*case R.id.idBtnSalir:

                Log.d("***", "Aplicacion finalizada");
                this.getActivity().finish();
                System.exit(0);
                break;*/
        }


    }
}
