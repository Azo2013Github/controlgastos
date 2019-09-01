package com.pgrsoft.controlgastos.services.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoServicesImpl implements MovimientoServices {

    private DataBaseHelper dataBaseHelper;
    private Context context;

    public MovimientoServicesImpl(Context context) {
        this.context = context;
        this.dataBaseHelper = new DataBaseHelper(this.context);

    }

    @Override
    public Movimiento create(Movimiento movimiento) {

        return this.dataBaseHelper.createMovimiento(movimiento);
    }

    @Override
    public List<Movimiento> getAll() {

        Cursor cursor = this.dataBaseHelper.getAllMovimientosCursor();

        List<Movimiento> movimientos = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0){

            while (cursor.moveToNext()){

                Long codigo = cursor.getLong(0);
                double importe = cursor.getDouble(1);
                String descripcion = cursor.getString(2);
                String strFecha = cursor.getString(3);
                double saldo = cursor.getDouble(4);
                Long codigoProducto = cursor.getLong(5);

                // Convertir el String en Date pour la fecha:
                Date fecha = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                // Habrá un try catch....
                strFecha = "21/08/1994";
                try {
                    fecha = sdf.parse(strFecha);
                }catch (ParseException e){
                    e.printStackTrace();
                }

                ProductoServices productoServices = new ProductoServicesImpl(this.context);
                Producto producto = productoServices.read(codigoProducto);
                Movimiento movimiento = new Movimiento(importe, descripcion, fecha, saldo, producto);

                movimiento.setCodigo(codigo);
                movimientos.add(movimiento);
                //Log.d("***",  movimientos.toString());
            }
        }

        dataBaseHelper.close();
        return movimientos;
    }

    @Override
    public Movimiento read(Long codigo) {

        Movimiento movimiento = null;

        Cursor cursor = this.dataBaseHelper.getMovimiento(codigo);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            Long code = cursor.getLong(0);
            double importe = cursor.getDouble(1);
            String descripcion = cursor.getString(2);
            String strFecha = cursor.getString(3);
            double saldo = cursor.getDouble(4);
            Long codigoProducto = cursor.getLong(5);

            // Transformar la fecha en Date()
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            // Habrá un try catch....
            try {
                fecha = sdf.parse(strFecha);
            }catch (ParseException e){
                e.printStackTrace();
            }

            ProductoServices productoServices = new ProductoServicesImpl(this.context);
            Producto producto = productoServices.read(codigoProducto);

            movimiento = new Movimiento (importe, descripcion, fecha, saldo, producto);
            movimiento.setCodigo(code);


        }

        //Log.d("**", "LEADO CODIGO: " +movimiento.toString());
        dataBaseHelper.close();
        return movimiento;
    }

    @Override
    public Movimiento update(Movimiento movimiento) {

        return dataBaseHelper.updatingMovimiento(movimiento);
    }

    @Override
    public boolean delete(Long codigo) {
        //Log.d("**", "BORANDO CODIGO: " + codigo);
        return dataBaseHelper.deletingMovimientoCodigo(codigo);
    }

 /* Transformar las fecha: */




}
