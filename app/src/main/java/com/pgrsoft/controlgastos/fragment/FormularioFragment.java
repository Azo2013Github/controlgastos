package com.pgrsoft.controlgastos.fragment;


import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.pgrsoft.controlgastos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends Fragment implements View.OnClickListener{

    private ImageButton comprar;
    private ImageButton pago;


    public FormularioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_formulario, container, false);

        comprar = (ImageButton) miVista.findViewById(R.id.idCompra);
        pago = (ImageButton) miVista.findViewById(R.id.idPagar);

        comprar.setOnClickListener(this);
        pago.setOnClickListener(this);



        return miVista;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.idCompra:
                Log.d("***", "COMPRAR");
                break;

            case R.id.idPagar:
                Log.d("***", "PAGAR");
                break;
        }


    }
}
