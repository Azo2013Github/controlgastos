package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.services.impl.CategoriaServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormularioActivity extends AppCompatActivity {

    //private List<Categoria> categorias;
    //private Categoria categoria;

    private ArrayAdapter<String> stringArrayAdapter;
    private CategoriaServices categoriaServices;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        categoriaServices = new CategoriaServicesImpl(this);

        spinner = (Spinner) findViewById(R.id.idSpinner);

        rellenarSpinner();

        //categorias = categoriaServices.getAll();


    }


    // Metodo para rellenar el spinner:
    private void rellenarSpinner(){

        List<Categoria> categorias = new ArrayList<>();

        Categoria categoria1 = new Categoria(1L, "Carne");
        Categoria categoria2 = new Categoria(2L, "Legumbre");
        Categoria categoria3 = new Categoria(3L, "Bebida");
        Categoria categoria4 = new Categoria(4L, "Fruta");
        Categoria categoria5 = new Categoria(5L, "Verduras");

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);

        String[] strNombres = new String[5];
        int i = 0;
        for (Categoria categoria: categorias) {
            strNombres[i] = categoria.getNombre();
            i++;

        }
        stringArrayAdapter = new ArrayAdapter<String>(FormularioActivity.this, android.R.layout.simple_list_item_1, strNombres);
        spinner.setAdapter(stringArrayAdapter);

    }


}
