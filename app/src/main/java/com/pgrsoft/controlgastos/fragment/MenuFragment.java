package com.pgrsoft.controlgastos.fragment;


import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pgrsoft.controlgastos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{


    private Button botonCrear;
    private Button botonListar;
    private Button botonSalir;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View miVista = inflater.inflate(R.layout.fragment_menu, container, false);

        botonCrear = (Button) miVista.findViewById(R.id.idCrear);
        botonListar = (Button) miVista.findViewById(R.id.idListar);
        botonSalir = (Button) miVista.findViewById(R.id.idSalir);

        botonSalir.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonSalir.setOnClickListener(this);


        return miVista;
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.idCrear:
                Log.d("***", "CREAR");
                break;

        }



    }
}
