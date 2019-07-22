package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;
import com.pgrsoft.controlgastos.services.impl.CategoriaServicesImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private SQLiteDatabase sql;
    private CategoriaServices categoriaServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        // Es necesario solicitar una instancia de SQLiteDatabase para entrar en onCreate o onUpgrade
        //sql = dataBaseHelper.getWritableDatabase();

        categoriaServices = new CategoriaServicesImpl(this);

        int numeroAleatorio = (int) (Math.random() * 10000);

        Categoria categoria = categoriaServices.create(new Categoria(null, "cat_" +numeroAleatorio));

        List<Categoria> categorias = categoriaServices.getAll();

        Log.d("***", categorias.toString());


        //

        //sql.close();


    }


}
