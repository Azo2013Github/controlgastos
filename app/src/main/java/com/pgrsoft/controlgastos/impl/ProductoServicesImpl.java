package com.pgrsoft.controlgastos.impl;

import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.ProductoServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoServicesImpl implements ProductoServices {

    private  static final ProductoServices INSTANCE = new ProductoServicesImpl();
    private static final Map<Long, Producto> PRODUCTOS;


    static {
        PRODUCTOS = new HashMap<Long, Producto>();
    }

    @Override
    public Producto create(Producto producto) {
        return null;
    }

    @Override
    public Producto read(long codigo) {
        return null;
    }

    @Override
    public List<Producto> getAll() {
        return null;
    }
}
