package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.CategoriaServicesImpl;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private CategoriaServices categoriaServices;
    private ProductoServices productoServices;
    private  MovimientoServices movimientoServices;

    private List<Categoria> categorias;
    private List<Producto> productos;
    private List<Movimiento> movimientos;

    private Categoria categoria;
    private Movimiento movimiento;
    private Producto producto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        // Es necesario solicitar una instancia de SQLiteDatabase para entrar en onCreate o onUpgrade
        /// SQLiteDatabase sql = dataBaseHelper.getWritableDatabase();

        categoriaServices = new CategoriaServicesImpl(this);

        int numeroAleatorio = (int) (Math.random() * 10000);

        categoria = categoriaServices.create(new Categoria(null, "cat_" + numeroAleatorio));

        categorias = categoriaServices.getAll();

        categoria = categoriaServices.read(5L);

        // la parte de productos:
        productoServices = new ProductoServicesImpl(this);
        producto = productoServices.create(new Producto
           (null, ("prod_" + numeroAleatorio), "buen_producto_" + numeroAleatorio, 20.3, categoria));

        productos = productoServices.getAll();

        producto = productoServices.read(2L);

        //numeroAleatorio = 0;

        movimientoServices = new MovimientoServicesImpl(this);

        movimiento = movimientoServices.create(new Movimiento(null, 12.4, "descrip_" + numeroAleatorio, new Date(), 710.10, producto));

        movimientos = movimientoServices.getAll();

        movimiento = movimientoServices.read(7L);

     }


}
