package com.pgrsoft.controlgastos.fragment;


import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
        Button btnEdit = (Button) miVista.findViewById(R.id.idBtnEdit);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ViewPager myViewPager = (ViewPager) getActivity().findViewById(R.id.idViewPager);
                myViewPager.setCurrentItem(3);

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("***", "Se ha clicado el botton");

                UpdateFragment fragment = new UpdateFragment();

                Bundle bundle = new Bundle();

                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

            }
        });

        return miVista;
    }

}
