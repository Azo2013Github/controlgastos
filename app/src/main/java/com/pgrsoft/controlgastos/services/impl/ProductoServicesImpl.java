package com.pgrsoft.controlgastos.services.impl;

import android.content.Context;

import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoServicesImpl implements ProductoServices {


    private DataBaseHelper myDB;

    public ProductoServicesImpl(Context context) {
        this.myDB = new DataBaseHelper(context);
    }

    @Override
    public Producto create(Producto producto) {

        return myDB.createProducto(producto);
    }

    @Override
    public List<Producto> getAll() {

        return myDB.getAllProducto();
    }

    @Override
    public Producto read(Long codigo) {

        return null;
    }


}
