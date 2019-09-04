package com.pgrsoft.controlgastos.adaptador;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.ProductoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;
import com.pgrsoft.controlgastos.services.impl.ProductoServicesImpl;

import java.util.List;

public class GastosAdaptador extends BaseAdapter {

    private Context contexto;
    private List<Producto> productos;
    private List<Movimiento> movimientos;

    private LayoutInflater inflater;
    private ProductoServices productoServices;
    private MovimientoServices movimientoServices;

    public GastosAdaptador(Context contexto){

        this.contexto = contexto;
        this.inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        productoServices = new ProductoServicesImpl(this.contexto);
        productos = productoServices.getAll();
        movimientoServices = new MovimientoServicesImpl(this.contexto);
        movimientos = movimientoServices.getAll();

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View miVista = inflater.inflate(R.layout.gasto_row_model, null);

        TextView textCategoria = (TextView) miVista.findViewById(R.id.idCategoria);
        TextView textNombre = (TextView) miVista.findViewById(R.id.idNombre);
        TextView textPrecio = (TextView) miVista.findViewById(R.id.idPrecio);
        ImageView imageView = (ImageView) miVista.findViewById(R.id.idImage);
        TextView textDescripcion = (TextView) miVista.findViewById(R.id.idDescripcion);

        Producto producto = productos.get(position);
        Movimiento movimiento = movimientos.get(position);

        textCategoria.setText(movimiento.getProducto().getCategoria().getNombre()); //producto.getCategoria().getNombre();
        textNombre.setText(movimiento.getProducto().getNombre());
        textPrecio.setText(String.valueOf(movimiento.getProducto().getPrecio()));
        textDescripcion.setText(String.valueOf(movimiento.getProducto().getDescripcion()));

        byte [] imagen = movimiento.getProducto().getImagen();
        Bitmap bitmap = getImage(imagen);

        //Convert bitmap to drawable
        Drawable drawable = new BitmapDrawable(miVista.getResources(), bitmap);
        imageView.setImageDrawable(drawable);
        imageView.setTag(position);

        return miVista;
    }

    @Override
    public int getCount() {

        return movimientos.size();
    }

        @Override
    public Object getItem(int i) {

        return movimientos.get(i);
    }

    @Override
    public long getItemId(int i) {

        return movimientos.get(i).getCodigo();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


}
