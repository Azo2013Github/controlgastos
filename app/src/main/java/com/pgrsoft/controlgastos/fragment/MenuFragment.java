package com.pgrsoft.controlgastos.fragment;


import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.adaptador.ViewPagerAdapters;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment /*implements View.OnClickListener*/{

    private Button btnCrear;
    private Button btnListarProducto;
    private Button btnListarMovimiento;
    private FloatingActionButton floatingActionButton;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View miVista = inflater.inflate(R.layout.fragment_menu, container, false);

        floatingActionButton = (FloatingActionButton) miVista.findViewById(R.id.idBtnAdd);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /*FormularioFragment formularioFragment = new FormularioFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, formularioFragment);

                fragmentTransaction.setTransition(fragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();*/

                ViewPager myViewPager = (ViewPager) getActivity().findViewById(R.id.idViewPager);
                myViewPager.setCurrentItem(3);



            }
        });

        /*btnCrear = (Button) miVista.findViewById(R.id.idBtnForm);
        btnListarProducto = (Button) miVista.findViewById(R.id.idBtnListar);
        btnListarMovimiento = (Button) miVista.findViewById(R.id.idBtnDiagrama);*/


        //btnCrear.setOnClickListener(this);
        //btnListarProducto.setOnClickListener(this);
        //btnListarMovimiento.setOnClickListener(this);

        return miVista;
    }

   /* @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.idBtnForm:

                FormularioFragment formularioFragment = new FormularioFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, formularioFragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;

            case R.id.idBtnListar:

                GastoListadoFragment gastoListadoFragment = new GastoListadoFragment();

                fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, gastoListadoFragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;

            case R.id.idBtnDiagrama:

                RecyclerFragment recyclerFragment = new RecyclerFragment();

                fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, recyclerFragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;

        }


    }*/
}
