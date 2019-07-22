package com.pgrsoft.controlgastos.services;

import com.pgrsoft.controlgastos.model.Producto;

import java.util.List;

public interface ProductoServices {

    public Producto create(Producto producto);
    public Producto read(Long codigo);

    public List<Producto> getAll();
}
