package com.pgrsoft.controlgastos.services.impl;

import android.content.Context;
import android.database.Cursor;

import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoriaServicesImpl implements CategoriaServices {

    private DataBaseHelper dataBaseHelper;

    public CategoriaServicesImpl(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }

    @Override
    public Categoria create(Categoria categoria) {

        return dataBaseHelper.createCategoria(categoria);
    }


    @Override
    public Categoria read(Long codigo) {

        Long codigo1 = codigo;

        Cursor cursor = dataBaseHelper.getCategoria(codigo1);

        Categoria categoria = null;

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToNext();
            Long code = cursor.getLong(0);
            String nombre = cursor.getString(1);

            categoria = new Categoria(nombre);
            categoria.setCodigo(code);

        }

        //dataBaseHelper.close();
        return categoria;
    }

    @Override
    public List<Categoria> getAll() {

        Cursor cursor = dataBaseHelper.getAllCategoriesCursor();

        List<Categoria> categorias = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Long codigo = cursor.getLong(0);
                String nombre = cursor.getString(1);

                Categoria categoria = new Categoria(nombre);
                categorias.add(categoria);
            }
        }
        dataBaseHelper.close();
        return categorias;
    }

    @Override
    public Categoria update(Categoria categoria) {

        return dataBaseHelper.updateCategoria(categoria);
    }

    @Override
    public boolean delete(Long codigo) {

        return dataBaseHelper.deletingCategoria(codigo);
    }
}
