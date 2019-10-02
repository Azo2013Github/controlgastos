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
import com.pgrsoft.controlgastos.services.CategoriaServices;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.CategoriaServicesImpl;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoDetalleFragment extends Fragment {

    private Button btnEditExpense;
    private ImageView imageView;

    public ListadoDetalleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_listado_detalle, container, false);

        TextView textNombre = (TextView) mView.findViewById(R.id.idDetalleNombre);
        TextView textDescripcion = (TextView) mView.findViewById(R.id.idDetalleDescripcion);
        TextView textImporte = (TextView) mView.findViewById(R.id.idImporte);
        TextView textFecha = (TextView) mView.findViewById(R.id.idFecha);
        imageView = (ImageView) mView.findViewById(R.id.idImageView);
        this.btnEditExpense = (Button) mView.findViewById(R.id.idBtnEdit);

        Bundle bundle = getArguments();
        if (bundle != null) {

            Movimiento movimiento = (Movimiento) bundle.getSerializable("MOVIMIENTOS");
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
                EditText editTextCategory = (EditText) dialog.findViewById(R.id.idEditTextCategory);
                EditText editTextName = (EditText) dialog.findViewById(R.id.idEditTextName);
                EditText editTextPrice = (EditText) dialog.findViewById(R.id.idEditTextPrice);
                EditText editTextAmount = (EditText) dialog.findViewById(R.id.idEditTextAmount);
                EditText editTextDescription = (EditText) dialog.findViewById(R.id.idEditTextDescrip);
                EditText editTextDescMov = (EditText) dialog.findViewById(R.id.idEditTextMovDesrip);


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
