package com.pgrsoft.controlgastos.services.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductoServicesImpl implements ProductoServices {


    private DataBaseHelper dataBaseHelper;
    private Context context = null;

    public ProductoServicesImpl(Context context) {
        this.context = context;
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    @Override
    public Producto create(Producto producto) {

        return dataBaseHelper.createProducto(producto);
    }

    @Override
    public List<Producto> getAll() {

        CategoriaServices categoriaServices = new CategoriaServicesImpl(this.context);

        Cursor cursor = dataBaseHelper.getAllProductosCursor();

        List<Producto> productos = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                Long codigo = cursor.getLong(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                double precio = cursor.getDouble(3);
                Long codigoCategoria = cursor.getLong(4);

                Categoria categoria = categoriaServices.read(codigoCategoria);
                Producto producto = new Producto(codigo, nombre, descripcion, precio, categoria);
                producto.setCodigo(codigo);
                productos.add(producto);


            }
        }
        //Log.d("Datos productos", productos.toString());
        dataBaseHelper.close();
        return productos;
    }



    @Override
    public Producto read(Long codigo) {

        Producto producto = null;
        CategoriaServices categoriaServices = new CategoriaServicesImpl(this.context);

        Cursor cursor = dataBaseHelper.getProducto(codigo);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            Long code = cursor.getLong(0);
            String nombre = cursor.getString(1);
            String descripcion = cursor.getString(2);
            double precio = cursor.getDouble(3);
            Long codigoCategoria = cursor.getLong(4);

            Categoria categoria = categoriaServices.read(codigoCategoria);

            producto = new Producto(codigo, nombre, descripcion, precio, categoria);
            producto.setCodigo(codigo);

            //Log.d("**", "LEYENDO UN PRODUCTO" +producto.toString());

        }

        dataBaseHelper.close();
        return producto;
    }

}
