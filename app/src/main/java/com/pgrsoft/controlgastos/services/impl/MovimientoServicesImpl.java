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
import java.util.Locale;

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
                Long codigoProducto = cursor.getLong(4);

                // Convertir el String en Date pour la fecha:
                Date fecha = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                try {
                    fecha = sdf.parse(strFecha);
                }catch (ParseException e){
                    e.printStackTrace();
                }

                ProductoServices productoServices = new ProductoServicesImpl(this.context);
                Producto producto = productoServices.read(codigoProducto);
                Movimiento movimiento = new Movimiento(importe, descripcion, fecha, producto);

                movimiento.setCodigo(codigo);
                movimientos.add(movimiento);
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
            Long codigoProducto = cursor.getLong(4);

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

            movimiento = new Movimiento (importe, descripcion, fecha, producto);
            movimiento.setCodigo(code);


        }

        dataBaseHelper.close();
        return movimiento;
    }

    @Override
    public Movimiento update(Movimiento movimiento) {

        return dataBaseHelper.updatingMovimiento(movimiento);
    }

    @Override
    public boolean delete(Long codigo) {

        return dataBaseHelper.deletingMovimientoCodigo(codigo);
    }

    @Override
    public Date getDateBetween(Date date) {

        Date fecha = null;
        Log.d("***", " Antes de Entrar ");
        List<Movimiento> movimientos = new ArrayList<>();
        Cursor cursor = dataBaseHelper.getDateBetweenQuery();
        if (cursor != null && cursor.getCount() > 0) {
            if  (cursor.moveToFirst()) {
                while (cursor.moveToNext()){
                    Long codigo = cursor.getLong(1);
                    double importe = cursor.getDouble(2);
                    String descripcion = cursor.getString(3);
                    String strFecha = cursor.getString(4);
                    Long coidgoProducto = cursor.getLong(5);

                    //convert tempUnixTime to Date
                    fecha = new java.util.Date(strFecha);

                    //create SimpleDateFormat formatter
                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);

                    try {
                        fecha = formatter1.parse(strFecha);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }

                    ProductoServices productoServices = new ProductoServicesImpl(this.context);
                    Producto producto = productoServices.read(coidgoProducto);
                    Movimiento movimiento = new Movimiento(importe, descripcion, fecha, producto);

                    movimiento.setCodigo(codigo);
                    movimientos.add(movimiento);


                }
            }
        }
        return fecha;
    }

}
