package com.pgrsoft.controlgastos.services.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;

import java.util.List;

public class CategoriaServicesImpl implements CategoriaServices {

    //private Context context;
    private DataBaseHelper myDB;

    public CategoriaServicesImpl(Context context) {
      //  this.context = context;
        this.myDB = new DataBaseHelper(context);
    }

    @Override
    public Categoria create(Categoria categoria) {

        SQLiteDatabase db = myDB.getWritableDatabase();
        Categoria createCategoria = myDB.createCategoria(categoria);
        //Log.d("***", createCategoria.toString());
        db.close();
        return createCategoria;
    }

    @Override
    public List<Categoria> getAll() {

        return myDB.getAll();
    }

    @Override
    public Categoria read(Long codigo) {
        return myDB.getCategoria(codigo);
    }

    @Override
    public Categoria update(Categoria categoria) {
        return null;
    }

    @Override
    public boolean delete(Long codigo) {
        return false;
    }

}
