package com.pgrsoft.controlgastos.fragment;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

//import com.frosquivel.magicalcamera.MagicalCamera;
//import com.frosquivel.magicalcamera.MagicalPermissions;
import com.pgrsoft.controlgastos.R;
import com.pgrsoft.controlgastos.activities.DiagramaActivity;
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
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends Fragment implements View.OnClickListener{

    private ImageButton btnCompra;
    private ImageButton btnPago;
    private ImageButton btnCamara;
    private ImageButton btnSave;

    private Spinner spinner;

    private EditText editNombre;
    private EditText editPrecio;
    private EditText editDescripcion;
    //private EditText editSaldo;
    private EditText editDesMovimiento;
    private EditText editCantidad;
    private ImageView imageView;
    private ImageView imageCamara;


    private Bitmap imageActual = null;

    private ArrayAdapter<String> stringArrayAdapter;
    private List<Categoria> categorias;
    private List<Producto> productos;
    private List<Movimiento> movimientos;

    private MovimientoServices movimientoServices;
    private ProductoServices productoServices;
    private CategoriaServices categoriaServices;

    private Categoria categoria;
    private Producto producto;
    private Movimiento movimiento;

    private final static int RESIZE_PHOTO_PIXEL_PERCENTAGE = 50; //esta variable sirve para la calidad de la imagen:
    //private MagicalPermissions magicalPermissions;
    //private MagicalCamera magicalCamera;

    public FormularioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_formulario, container, false);

        btnCompra = (ImageButton) miVista.findViewById(R.id.idCompra);
        btnPago = (ImageButton) miVista.findViewById(R.id.idPagar);
        btnCamara = (ImageButton) miVista.findViewById(R.id.idCamera);
        btnSave = (ImageButton) miVista.findViewById(R.id.idSave);

        spinner = (Spinner) miVista.findViewById(R.id.idCategoria);
        cargarSpinner();

        editNombre = (EditText) miVista.findViewById(R.id.idNombre);
        editPrecio = (EditText) miVista.findViewById(R.id.idPrecio);
        editDescripcion = (EditText) miVista.findViewById(R.id.idDescripcion);
        // editSaldo = (EditText) miVista.findViewById(R.id.idSaldo);
        editCantidad = (EditText) miVista.findViewById(R.id.idCantidad);
        editDesMovimiento = (EditText) miVista.findViewById(R.id.idDesMovimiento);
        imageView = (ImageView) miVista.findViewById(R.id.idImage);
        imageCamara = (ImageView) miVista.findViewById(R.id.idImageCamara);

        categoriaServices = new CategoriaServicesImpl(this.getActivity());
        productoServices = new ProductoServicesImpl(this.getActivity());
        movimientoServices = new MovimientoServicesImpl(this.getActivity());

        /*Introducimos un Saldo inicial en la applicacion:
         * Desde la primera pantalla que es MenuFragment: */
        //Bundle bundle = getArguments();
        //double saldo = bundle.getDouble("SALDO");

        categorias = new ArrayList<>();
        productos = new ArrayList<>();
        movimientos = new ArrayList<>();

        /*movimientos = movimientoServices.getAll();
        int indice = movimientos.size() -1 ;
        saldo = saldo + movimientos.get(indice).getSaldo();*/

        //editSaldo.setText(String.valueOf(saldo));

        //movimientos.clear();

        /***********************************************/
        //hacerFoto();

        btnCompra.setOnClickListener(this);
        btnPago.setOnClickListener(this);
        btnCamara.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        return miVista;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.idCompra:

                String strCategoria = spinner.getSelectedItem().toString();
                String nombre = editNombre.getText().toString();
                double precio = Double.parseDouble(editPrecio.getText().toString());
                double importe = Double.parseDouble(editCantidad.getText().toString()) * Double.parseDouble(editPrecio.getText().toString());

                String descripcion = editDescripcion.getText().toString();
                String desMovimiento = editDesMovimiento.getText().toString();
                //double saldo = Double.parseDouble(editSaldo.getText().toString()) - importe;

                categoria = new Categoria(strCategoria);
                byte[] image = changeImageListView(categoria.getNombre()); //Guardar las categorias
                // en funcion de las Imagenes para añadir en la BBDD.
                producto = new Producto(nombre, descripcion, precio, image, categoria);
                movimiento = new Movimiento(importe, desMovimiento, new Date(), producto);

                categoriaServices.create(categoria);
                productoServices.create(producto);
                movimientoServices.create(movimiento);

                /*categorias.add(categoria);
                productos.add(producto);
                movimientos.add(movimiento);*/



                vaciarEditText();

                break;

            case R.id.idPagar:
               /*for (int i=0; i< categorias.size(); i++) {

                }*/

                // Necesitamos una fecha de inicio y una de fin.
                // Esto es "hardcodeado" para pruebas....
                String strFechaInicio = "10/12/1990";
                String strFechaFin = "5/9/2019";
                Date fechaInicio = null;
                Date fechaFin = null;
                SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");

                try {
                    fechaInicio = sdf.parse(strFechaInicio);
                    fechaFin = sdf.parse(strFechaFin);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                movimientos = movimientoServices.getDateBetween(fechaInicio, fechaFin);

                Log.d("***", "MOVIMIENTOS ARRAY: " +movimientos.toString());

                // movimientos contiene el listado de movimientos entre fechas (admás ordenado)

                // Ahora simplemente vamos a constatar que los datos son correctos....





                //i++;
                //Movimiento movimiento = movimientos.get(i);

                Bundle bundle = new Bundle();

                //bundle.putSerializable("PRODUCTOS", producto);
                //bundle.putSerializable("MOVIMIENTOS", movimiento);

                Intent intent = new Intent (this.getActivity(), DiagramaActivity.class);
                intent.putExtra("MOVIMIENTOS", movimiento);
                startActivity(intent);



                Fragment fragment = new ListadoDetalleFragment();


                break;
            case R.id.idCamera:

                // Hacer foto llamando al metodo abrir camara:
                abrirCamara();

                //magicalCamera.takeFragmentPhoto(FormularioFragment.this);
                //fragment = new ListadoDetalleFragment();
                //magicalCamera.takeFragmentPhoto(fragment);
                //magicalCamera.takeFragmentPhoto(FormularioFragment.this);
                //startActivityForResult(magicalCamera.getIntentFragment(), magicalCamera.TAKE_PHOTO);

                break;

            case R.id.idSave:
                // Guardar las imagenes en un fichero:
                guardarFoto();

                break;
        }

    }

    // convert from bitmap to byte array para guardar las imagen en la bbdd
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    //Codigo anterior que no funciona :
    /*private void hacerFoto(){
         ****************************
        Esta parte sirve para hacer foto y con el boton btnCamara guardamos las imagenes que hemos echo:
        String [] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        // magicalPermissions = new MagicalPermissions(this,permissions);
        // magicalCamera = new MagicalCamera(this.getActivity(), RESIZE_PHOTO_PIXEL_PERCENTAGE, magicalPermissions);
    }*/


    /* Funciones a implementar para usar la camara son:
    * 1) */

    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("***", "ENTRA AQUI resultCode: ");
        if (resultCode == RESULT_OK){

            /*magicalCamera.resultPhoto(requestCode, resultCode, data);
            imageView.setImageBitmap(magicalCamera.getPhoto());

            String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),
                          "myPhotoName",
                        "myDirectoryName",
                                      magicalCamera.JPEG,
                    true);


        }
    }*/

    private void abrirCamara(){

        // Se trata de un Intent ya definido en el sistema .....
        Intent hacerFotoIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);

        // estamos comprobando que la aplicacion de la camara se puede abrir con normalidad
        if (hacerFotoIntent.resolveActivity(getActivity().getPackageManager()) != null){

            // abrir la camara.. el requesCode es 1 que nos dice si volvemos en la activitdad el codigo dw la actividad
            startActivityForResult(hacerFotoIntent, 1);



        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK){ // RESULT_OK devuelve -1 si las cosas han ido bien

            Bundle extras = data.getExtras(); // Ya tengo referencia al Bundle a partir de Intent
            Bitmap imageBitmap = (Bitmap) extras.get("data"); // Lo de "data" hay que saberlo..
            imageCamara.setImageBitmap(imageBitmap);

            // Como posiblemente guarde este bitmap en el sistema de archivos
            // me interesara tb guardar el bitmap en la variable de instancia de esta Actvity
            imageActual = imageBitmap;

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
    private void guardarFoto(){

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

    }

    private void cargarSpinner(){

        categorias = new ArrayList<>();

        Categoria categoria1 = new Categoria("CARNE");
        Categoria categoria2 = new Categoria("VERDURA");
        Categoria categoria3 = new Categoria("SUMINISTROS");
        Categoria categoria4 = new Categoria("LEGUMBRE");
        Categoria categoria5 = new Categoria("BEBIDAS");
        Categoria categoria6 = new Categoria("ROPA");
        Categoria categoria7 = new Categoria("ALQUILER");
        Categoria categoria8 = new Categoria("EXTRAS");
        Categoria categoria9 = new Categoria("FARMACIA");

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);
        categorias.add(categoria6);
        categorias.add(categoria7);
        categorias.add(categoria8);
        categorias.add(categoria9);
        int i = 0;
        String [] strNombres = new String [9];

        for (Categoria categoria: categorias) {
            strNombres [i] = categoria.getNombre(); //cogemos los nombres de los agentes en cada posicion
            i++; //Incrementamos el i para que salga todos los nombres de los agentes
        }
        stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, strNombres);
        spinner.setAdapter(stringArrayAdapter);

    }

    /* Esta funcion sirve qua que las imagenes vayan cambiando segun categoria */
    private byte[] changeImageListView(String nombre){

        switch (nombre){

            case "CARNE":
                imageView.setImageResource(R.drawable.carne);
                break;
            case "VERDURA":
                imageView.setImageResource(R.drawable.verduras);
                break;
            case"SUMINISTROS":
                imageView.setImageResource(R.drawable.grifo);
                break;
            case"LEGUMBRE":
                imageView.setImageResource(R.drawable.legumbres);
                break;
            case"BEBIDAS":
                imageView.setImageResource(R.drawable.bebidas);
                break;
            case"ROPA":
                imageView.setImageResource(R.drawable.ropa);
                break;
            case"ALQUILER":
                imageView.setImageResource(R.drawable.casa);
                break;
            case"EXTRAS":
                imageView.setImageResource(R.drawable.extras);
                break;
            case "FARMACIA":
                imageView.setImageResource(R.drawable.farmacia);
                break;

        }
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        byte [] imagen = getBytes(bitmap);
        return imagen;
    }

    private void vaciarEditText(){
        //editSaldo.setText("");
        editDescripcion.setText("");
        editCantidad.setText("");
        editPrecio.setText("");
        editNombre.setText("");
        editDesMovimiento.setText("");
    }

}
