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

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                Long codigo = cursor.getLong(0);
                double importe = cursor.getDouble(1);
                String descripcion = cursor.getString(2);
                Date fecha = getDateFromMilliseconds(cursor.getString(3));
                Long codigoProducto = cursor.getLong(4);

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

            Date fecha = getDateFromMilliseconds(cursor.getString(3));
            Long codigoProducto = cursor.getLong(4);

            ProductoServices productoServices = new ProductoServicesImpl(this.context);
            Producto producto = productoServices.read(codigoProducto);
            movimiento = new Movimiento(importe, descripcion, fecha, producto);
            movimiento.setCodigo(code);
        }

        dataBaseHelper.close();
        return movimiento;
    }

    @Override
    public boolean delete(Long codigo) {

        return dataBaseHelper.deletingMovimientoCodigo(codigo);
    }

    @Override
    public List<Movimiento> getDateBetween(Date dateInicial, Date dateFinal) {

        List<Movimiento> movimientos = new ArrayList<>();

        Cursor cursor = dataBaseHelper.getDateBetweenQuery(dateInicial, dateFinal);

        if (cursor == null){
            Log.d("***c", "CURSOR es Nulo: " +cursor.getCount());
        }
        if (cursor != null && cursor.getCount() > 0) {
            Log.d("*** c", "CURSOR: " +cursor.getCount());
            while (cursor.moveToNext()) {
                Long codigo = cursor.getLong(0);
                double importe = cursor.getDouble(1);
                String descripcion = cursor.getString(2);
                Date fecha = getDateFromMilliseconds(cursor.getString(3));
                Long codigoProducto = cursor.getLong(4);

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

    private Date getDateFromMilliseconds(String strMilliseconds){
        return new Date(Long.parseLong(strMilliseconds));
    }

    @Override
    public Movimiento update(Movimiento movimiento) {

        return dataBaseHelper.updatingMovimiento(movimiento);
    }

}


