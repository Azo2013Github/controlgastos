package com.pgrsoft.controlgastos.adaptador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Categoria;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.CategoriaServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.List;

public class ProductosAdaptador extends BaseAdapter {

    private Context contexto;
    private List<Producto> productos;

    private LayoutInflater inflater;
    private ProductoServices productoServices;

    public ProductosAdaptador(Context contexto){

        this.contexto = contexto;
        //this.productos = productos;
        this.inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        productoServices = new ProductoServicesImpl(this.contexto);
        productos = productoServices.getAll();

    }



    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View miVista = inflater.inflate(R.layout.listado_row_model, null);

        TextView textCategoria = (TextView) miVista.findViewById(R.id.idCategoria);
        TextView textNombre = (TextView) miVista.findViewById(R.id.idNombre);
        TextView textPrecio = (TextView) miVista.findViewById(R.id.idPrecio);
        ImageView imageView = (ImageView) miVista.findViewById(R.id.idImage);

        //TextView textDescripcion = (TextView) miVista.findViewById(R.id.idDescripcion);


        Producto producto = productos.get(position);

        Log.d("***", "Productos no es null: " + producto.getNombre());

        textCategoria.setText(producto.getCategoria().getNombre());

        textNombre.setText(producto.getNombre());
        textPrecio.setText(String.valueOf(producto.getPrecio()));

        imageView.setImageResource(R.drawable.bebidas);

        return miVista;
    }

    @Override
    public int getCount() {

        return productos.size();
    }

    @Override
    public Object getItem(int i) {

        return productos.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return productos.get(i).getCodigo();
    }


}
