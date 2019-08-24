package com.pgrsoft.controlgastos.fragment;


import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Categoria;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends Fragment implements View.OnClickListener{

    private ImageButton comprar;
    private ImageButton pago;
    private Spinner spinner;
    private EditText editNombre;
    private EditText editPrecio;
    private EditText editDescripcion;
    private EditText editImporte;
    private EditText editFecha;
    private EditText editSaldo;



    private ArrayAdapter<String> stringArrayAdapter;


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

        spinner = (Spinner) miVista.findViewById(R.id.idCategoria);

        editNombre = (EditText) miVista.findViewById(R.id.idNombre);
        editPrecio = (EditText) miVista.findViewById(R.id.idPrecio);
        editDescripcion = (EditText) miVista.findViewById(R.id.idDescripcion);
        editImporte = (EditText) miVista.findViewById(R.id.idImporte);
        editFecha = (EditText) miVista.findViewById(R.id.idFecha);
        editSaldo = (EditText) miVista.findViewById(R.id.idSaldo);



        cargarSpinner();
        comprar.setOnClickListener(this);
        pago.setOnClickListener(this);



        return miVista;
    }



    @Override
    public void onClick(View view) {

        Fragment fragment;

        switch (view.getId()){

            case R.id.idCompra:
                Log.d("***", "COMPRAR");
                Toast.makeText(getActivity(), "SE HA RECUPERADO EL ID", Toast.LENGTH_LONG).show();

                fragment = new ListadoFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.destino, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;

            case R.id.idPagar:
                Log.d("***", "PAGAR");
                break;
        }

    }


    private void cargarSpinner(){

        List<Categoria> categorias = new ArrayList<>();

        Categoria categoria1 = new Categoria(1L, "CARNE");
        Categoria categoria2 = new Categoria(2L, "VERDURA");
        Categoria categoria3 = new Categoria(3L, "SINISTROS");
        Categoria categoria4 = new Categoria(4L, "LEGUMBRE");
        Categoria categoria5 = new Categoria(5L, "BEBIDAS");
        Categoria categoria6 = new Categoria(6L, "ROPA");
        Categoria categoria7 = new Categoria(7L, "EXTRAS");
        Categoria categoria8 = new Categoria(8L, "ALQUILER");

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);
        categorias.add(categoria6);
        categorias.add(categoria7);
        categorias.add(categoria8);

        int i = 0;
        String [] strNombres = new String [8];

        for (Categoria categoria: categorias) {
            strNombres [i] = categoria.getNombre(); //cogemos los nombres de los agentes en cada posicion
            Toast.makeText(getActivity(), strNombres[i], Toast.LENGTH_LONG).show();
            i++; //Incrementamos el i para que salga todos los nombres de los agentes
        }

        stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, strNombres);

        spinner.setAdapter(stringArrayAdapter);

    }

}
