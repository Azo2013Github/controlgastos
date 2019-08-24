package com.pgrsoft.controlgastos.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.List;

public class ProductosAdaptador extends BaseAdapter {

    private Context contexto;
    private List<Producto> productos;

    private LayoutInflater inflater;
    private ProductoServices productoServices;

    public ProductosAdaptador(Context contexto, List<Producto> productos){

        this.contexto = contexto;
        this.productos = productos;
        this.inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        productoServices = new ProductoServicesImpl(this.contexto);

    }



    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View miVista = inflater.inflate(R.layout.listado_row_model, null);

        TextView categoria = (TextView) miVista.findViewById(R.id.idCategoria);
        TextView nombre = (TextView) miVista.findViewById(R.id.idNombre);
        TextView precio = (TextView) miVista.findViewById(R.id.idPrecio);
        TextView descripcion = (TextView) miVista.findViewById(R.id.idDescripcion);

        Producto producto = productos.get(position);

        categoria.setText(producto.getCategoria().getNombre());
        nombre.setText(producto.getNombre());
        precio.setText(String.valueOf(producto.getPrecio()));
        //saldo.setText(String.valueOf(producto.get()));

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
