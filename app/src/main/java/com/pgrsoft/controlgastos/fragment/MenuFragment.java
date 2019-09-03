package com.pgrsoft.controlgastos.fragment;


import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pgrsoft.controlgastos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{


    private Button btnCrear;
    private Button btnListarProducto;
    private Button btnListarMovimiento;

    private EditText editTextSaldo;

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
        btnListarMovimiento = (Button) miVista.findViewById(R.id.idBtnMovimiento);
        editTextSaldo = (EditText) miVista.findViewById(R.id.idSaldo);

        btnCrear.setOnClickListener(this);
        btnListarProducto.setOnClickListener(this);
        btnListarMovimiento.setOnClickListener(this);

        return miVista;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.idBtnCrear:

                double saldo = Double.parseDouble(editTextSaldo.getText().toString());

                Bundle bundle = new Bundle();

                bundle.putDouble("SALDO", saldo);

                fragment = new FormularioFragment();

                fragment.setArguments(bundle);

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

        }


    }
}
