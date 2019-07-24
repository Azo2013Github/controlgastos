package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.CategoriaServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.CategoriaServicesImplBK;
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private CategoriaServices categoriaServices;
    private ProductoServices productoServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        // Es necesario solicitar una instancia de SQLiteDatabase para entrar en onCreate o onUpgrade
        /// SQLiteDatabase sql = dataBaseHelper.getWritableDatabase();

        categoriaServices = new CategoriaServicesImpl(this);

        int numeroAleatorio = (int) (Math.random() * 10000);

        Categoria categoria = categoriaServices.create(new Categoria(null, "cat_" + numeroAleatorio));

        List<Categoria> categorias = categoriaServices.getAll();

        categoria = categoriaServices.read(7L);

        // la parte de productos:
        productoServices = new ProductoServicesImpl(this);
        Producto producto = productoServices.create(new Producto
                (null, ("prod_" + numeroAleatorio), "buen_producto_" + numeroAleatorio, 12.3, categoria));

        List<Producto> productos = productoServices.getAll();

        producto = productoServices.read(1L);



    }


}
