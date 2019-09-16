package com.pgrsoft.controlgastos.fragment;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.app.Fragment;

import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.model.Movimiento;
import com.pgrsoft.controlgastos.model.Producto;
import com.pgrsoft.controlgastos.services.MovimientoServices;
import com.pgrsoft.controlgastos.services.impl.MovimientoServicesImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoDetalleFragment extends Fragment implements View.OnClickListener{

    private Button btnPhoto;
    private Button btnSave;
    private Button btnEstatistic;
    private Bitmap imageActual = null;
    private ImageView imageView;

    private MovimientoServices movimientoServices;
    private List<Movimiento> movimientos;

    public ListadoDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int i = 0;
        View miVista = inflater.inflate(R.layout.fragment_listado_detalle, container, false);

        //movimientoServices = new MovimientoServicesImpl(this.getActivity());

        TextView textNombre = (TextView) miVista.findViewById(R.id.idDetalleNombre);
        TextView textDescripcion = (TextView) miVista.findViewById(R.id.idDetalleDescripcion);
        TextView textImporte = (TextView) miVista.findViewById(R.id.idImporte);
        TextView textFecha = (TextView) miVista.findViewById(R.id.idFecha);
        imageView = (ImageView) miVista.findViewById(R.id.idImageView);

        btnPhoto = (Button) miVista.findViewById(R.id.idBtnPhoto);
        btnSave = (Button) miVista.findViewById(R.id.idBtnSavePhoto);
        btnEstatistic = (Button) miVista.findViewById(R.id.idBtnEstatistic);

        Bundle bundle = getArguments();

        if (bundle != null) {

            Producto producto = (Producto) bundle.getSerializable("PRODUCTOS");
            Movimiento movimiento = (Movimiento) bundle.getSerializable("MOVIMIENTOS");

            textNombre.setText(producto.getNombre());
            textDescripcion.setText(movimiento.getDescripcion());
            textImporte.setText(String.valueOf(movimiento.getImporte()));
            textFecha.setText(String.valueOf(movimiento.getFecha()));

            getDrawableImage(movimiento.getProducto().getImagen(), imageView) ;

        }

        btnPhoto.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnEstatistic.setOnClickListener(this);

        return miVista;
    }


    @Override
    public void onClick(View view) {

          switch (view.getId()){

              case R.id.idBtnPhoto:
                  getCamera();
                  break;

              case R.id.idBtnSavePhoto:
                  savePhoto();
                  break;
              case R.id.idBtnEstatistic:
                  Fragment fragment = new EstadisticaFragment();

                  FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                  fragmentTransaction.replace(R.id.destino, fragment);

                  fragmentTransaction.addToBackStack(null);

                  fragmentTransaction.commit();

                  break;
          }
    }


    /* La funcion para cambiar las imagenes: */
    private void getDrawableImage(int valueImage, ImageView imageView){

        switch (valueImage){
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
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK){ // RESULT_OK devuelve -1 si las cosas han ido bien


            Bundle extras = data.getExtras(); // Ya tengo referencia al Bundle a partir de Intent
            Bitmap imageBitmap = (Bitmap) extras.get("data"); // Lo de "data" hay que saberlo..
            imageView.setImageBitmap(imageBitmap);
            // Como posiblemente guarde este bitmap en el sistema de archivos
            // me interesara tb guardar el bitmap en la variable de instancia de esta Actvity
            imageActual = imageBitmap;

        }

    }


    private void getCamera(){

        // Se trata de un Intent ya definido en el sistema .....
        Intent takePhotoIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);

        // estamos comprobando que la aplicacion de la camara se puede abrir con normalidad
        if (takePhotoIntent.resolveActivity(getActivity().getPackageManager()) != null){

            // abrir la camara.. el requesCode es 1 que nos dice si volvemos en la activitdad el codigo dw la actividad
            startActivityForResult(takePhotoIntent, 1);
            Log.d("***", "entra en el metodo getCamera()");
        }

    }

    /* Guardar las fotos en un fichero: */
    // Vamos a necesitar 2 methodos 1 que camboara el nombre de la imagen para que no haya conflicto..
    private File createImageFile() throws IOException {

        String strName = "name" + ((int)(Math.random() * 10000)) ;

        // El constructor de File  necesita saber
        // 1. - El directorio (en este caso el directorio de nuestra app)
        // 2. - Necesita conocer el nombre del archivo...
        File file = new File(this.getActivity().getFilesDir(), strName);
        return file;
    }

    // La segundo metodo que guarde fotos:
    private void savePhoto(){

        try{
            // lo primero que necesitamos es el File
            File file = createImageFile();
            Log.d("**", "File: " +file.getAbsolutePath());
            // Un FileOutputStream IS-A OutputStream espacializada en archivos...
            OutputStream out = new FileOutputStream(file);
            // Ahora vamos a "enviar" la imagen actial a traves de stream imageActual
            imageActual.compress(Bitmap.CompressFormat.JPEG, 100 , out);
            out.flush(); // El flush() lleva la orden que la imagen  "fluya por la tuberia"
            out.close(); // y
        }catch (Exception e){
            e.printStackTrace();
        }
        /* Intent intent = getIntent();
 selectedItem = (ListItem) intent.getSerializableExtra("selectedItem");
 byte[] byteArray = getIntent().getByteArrayExtra("image");
 Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0,
 byteArray.length);
 selectedItem.setPhoto(image);*/

    }

}
