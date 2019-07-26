package com.pgrsoft.controlgastos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
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
import com.pgrsoft.controlgastos.sqlite.DataBaseHelper;

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

        /*DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        // Es necesario solicitar una instancia de SQLiteDatabase para entrar en onCreate o onUpgrade
        SQLiteDatabase sql = dataBaseHelper.getWritableDatabase();
        sql.close();*/

        categoriaServices = new CategoriaServicesImpl(this);

        int numeroAleatorio = (int) (Math.random() * 10000);

        categoria = categoriaServices.create(new Categoria(null, "cat_" + numeroAleatorio));
        //Log.d("**", "MainActivity Categoria crear: " + categoria.toString());

        categorias = categoriaServices.getAll();
        //Log.d("**", "MainActivity Categoria ALL: " + categorias.toString());

        categoria = categoriaServices.read(11L);
        //Log.d("**", "MainActivity Categoria codigo: "+categoria.getCodigo().toString());

        // la parte de productos:
        productoServices = new ProductoServicesImpl(this);
        producto = productoServices.create(new Producto
           (null, ("prod_" + numeroAleatorio), "buen_producto_" + numeroAleatorio, 4.3, categoria));

        //Log.d("**", "MainActivity Producto Crear: " +producto.toString());

        productos = productoServices.getAll();
        //Log.d("**", "MainActivity Productos All: " + productos.toString());

        producto = productoServices.read(5L);

        //Log.d("**", "MainActivity Producto Codigo: " +producto.toString());

        //numeroAleatorio = 0;

        movimientoServices = new MovimientoServicesImpl(this);

        movimiento = movimientoServices.create(new Movimiento(null, 1.4, "descrip_" + numeroAleatorio, new Date(), 100.10, producto));

        //Log.d("**", "MainActivity Movimiento Crear: " + movimiento.toString());

        movimientos = movimientoServices.getAll();

        Log.d("***" , "Main: " + movimientos.toString());

        /*  movimiento = movimientoServices.read(7L);*/

     }


}
