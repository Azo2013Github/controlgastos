package com.pgrsoft.controlgastos.services;

import com.pgrsoft.controlgastos.model.Categoria;

import java.util.List;

public interface CategoriaServices {

    // añadido desde el poratil

    public Categoria create(Categoria categoria);       // 1
    public Categoria update(Categoria categoria);       // TODO
    public boolean delete(Long codigo);                 // TODO
    public Categoria read(Long codigo);                 // 3

    public List<Categoria> getAll();                    // 2

}
