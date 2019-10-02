package com.pgrsoft.controlgastos.fragment;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoDetalleFragment extends Fragment {

    private Button btnEditExpense;

    private ImageView imageView;
    private Movimiento movimiento;

    public ListadoDetalleFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View mView = inflater.inflate(R.layout.fragment_listado_detalle, container, false);

        TextView textNombre = (TextView) mView.findViewById(R.id.idDetalleNombre);
        TextView textDescripcion = (TextView) mView.findViewById(R.id.idDetalleDescripcion);
        TextView textImporte = (TextView) mView.findViewById(R.id.idImporte);
        TextView textFecha = (TextView) mView.findViewById(R.id.idFecha);
        imageView = (ImageView) mView.findViewById(R.id.idImageView);
        this.btnEditExpense = (Button) mView.findViewById(R.id.idBtnEdit);

        Bundle bundle = getArguments();
        if (bundle != null) {

            movimiento = (Movimiento) bundle.getSerializable("MOVIMIENTOS");
            textNombre.setText(movimiento.getProducto().getNombre());
            textDescripcion.setText(movimiento.getDescripcion());
            textImporte.setText(String.valueOf(movimiento.getImporte()));
            textFecha.setText(String.valueOf(movimiento.getFecha()));
            getDrawableImage(movimiento.getProducto().getImagen(), imageView);
        }

        this.btnEditExpense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.updating_fragment);
                final EditText editTextCategory = (EditText) dialog.findViewById(R.id.idEditTextCategory);
                final EditText editTextName = (EditText) dialog.findViewById(R.id.idEditTextName);
                final EditText editTextPrice = (EditText) dialog.findViewById(R.id.idEditTextPrice);
                final EditText editTextAmount = (EditText) dialog.findViewById(R.id.idEditTextAmount);
                final EditText editTextDescription = (EditText) dialog.findViewById(R.id.idEditTextDescrip);
                final EditText editTextDescMov = (EditText) dialog.findViewById(R.id.idEditTextMovDesrip);

                editTextCategory.setText(movimiento.getProducto().getCategoria().getNombre());
                editTextName.setText(movimiento.getProducto().getNombre());
                editTextPrice.setText(String.valueOf(movimiento.getProducto().getPrecio()));
                editTextAmount.setText(String.valueOf(movimiento.getImporte()));
                editTextDescription.setText(movimiento.getProducto().getDescripcion());
                editTextDescMov.setText(movimiento.getDescripcion());
                getDrawableImage(movimiento.getProducto().getImagen(), imageView);

                Button btnSave = (Button) dialog.findViewById(R.id.idBtnSaveList);
                btnSave.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        CategoriaServices categoriaServices = new CategoriaServicesImpl(getActivity());
                        ProductoServices productoServices = new ProductoServicesImpl(getActivity());
                        MovimientoServices movimientoServices = new MovimientoServicesImpl(getActivity());
                        double importe = Double.parseDouble(editTextAmount.getText().toString());
                        double price = Double.parseDouble(editTextPrice.getText().toString());
                        String category = editTextCategory.getText().toString();
                        String descripcion = editTextDescription.getText().toString();
                        String descripMov = editTextDescMov.getText().toString();
                        String name = editTextName.getText().toString();

                        movimiento.setImporte(importe);
                        movimiento.setDescripcion(descripMov);
                        movimiento.getProducto().setNombre(name);
                        movimiento.getProducto().setDescripcion(descripcion);
                        movimiento.getProducto().setPrecio(price);
                        movimiento.getProducto().getCategoria().setNombre(category);
                        getDrawableImage(movimiento.getProducto().getImagen(), imageView);

                        Categoria categoria = movimiento.getProducto().getCategoria();
                        Producto producto = movimiento.getProducto();
                        Log.d("Valores de Movimeinto: " , movimiento.toString());

                        categoria = categoriaServices.update(categoria);
                        producto = productoServices.update(producto);
                        movimiento = movimientoServices.update(movimiento);
                    }
                });

                dialog.show();

            }


        });





        return mView;
    }

    /* La funcion para cambiar las imagenes: */
    private void getDrawableImage(int valueImage, ImageView imageView) {

        switch (valueImage) {
            case 1:
                imageView.setImageResource(R.drawable.carne);
                break;
            case 2:
                imageView.setImageResource(R.drawable.verduras);
                break;
            case 3:
                imageView.setImageResource(R.drawable.grifo);
                break;
            case 4:
                imageView.setImageResource(R.drawable.legumbres);
                break;
            case 5:
                imageView.setImageResource(R.drawable.bebidas);
                break;
            case 6:
                imageView.setImageResource(R.drawable.ropa);
                break;
            case 7:
                imageView.setImageResource(R.drawable.casa);
                break;
            case 8:
                imageView.setImageResource(R.drawable.extras);
                break;
            case 9:
                imageView.setImageResource(R.drawable.farmacia);
                break;
            case 10:
                imageView.setImageResource(R.drawable.shoes);
                break;
            case 11:
                imageView.setImageResource(R.drawable.ic_tmb);
                break;
            case 12:
                imageView.setImageResource(R.drawable.ic_fruta);
                break;
        }

    }

}
