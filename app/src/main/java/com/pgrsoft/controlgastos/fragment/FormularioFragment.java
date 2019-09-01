package com.pgrsoft.controlgastos.fragment;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends Fragment implements View.OnClickListener{

    private ImageButton botonComprar;
    private ImageButton botonPagar;
    private ImageButton botonGuardar;

    private Spinner spinner;

    private EditText editNombre;
    private EditText editPrecio;
    private EditText editDescripcion;
    private EditText editImporte;
    private EditText editFecha;
    private EditText editSaldo;
    private EditText editDesMovimiento;
    private ImageView imageView;

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
    private MagicalPermissions magicalPermissions;
    private MagicalCamera magicalCamera;

    public FormularioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miVista = inflater.inflate(R.layout.fragment_formulario, container, false);

        botonComprar = (ImageButton) miVista.findViewById(R.id.idCompra);
        botonPagar = (ImageButton) miVista.findViewById(R.id.idPagar);
        botonGuardar = (ImageButton) miVista.findViewById(R.id.idGuardar);

        spinner = (Spinner) miVista.findViewById(R.id.idCategoria);
        cargarSpinner();

        editNombre = (EditText) miVista.findViewById(R.id.idNombre);
        editPrecio = (EditText) miVista.findViewById(R.id.idPrecio);
        editDescripcion = (EditText) miVista.findViewById(R.id.idDescripcion);
        editImporte = (EditText) miVista.findViewById(R.id.idImporte);
        editFecha = (EditText) miVista.findViewById(R.id.idFecha);
        editSaldo = (EditText) miVista.findViewById(R.id.idSaldo);
        editDesMovimiento = (EditText) miVista.findViewById(R.id.idDesMovimiento);
        imageView = (ImageView) miVista.findViewById(R.id.idImage);

        //imageView.setImageResource(R.drawable.bebidas);

        categorias = new ArrayList<>();
        productos = new ArrayList<>();
        movimientos = new ArrayList<>();

        /***********************************************/
        hacerFoto();

        botonComprar.setOnClickListener(this);
        botonPagar.setOnClickListener(this);
        botonGuardar.setOnClickListener(this);

        return miVista;
    }

    @Override
    public void onClick(View view) {

        Fragment fragment;

        switch (view.getId()){

            case R.id.idCompra:

                String strCategoria = spinner.getSelectedItem().toString();
                String nombre = editNombre.getText().toString();
                double precio = Double.parseDouble(editPrecio.getText().toString());
                double importe = Double.parseDouble(editImporte.getText().toString());

                String descripcion = editDescripcion.getText().toString();
                String desMovimiento = editDesMovimiento.getText().toString();
                double saldo = Double.parseDouble(editSaldo.getText().toString());

                categoria = new Categoria(strCategoria);

                byte[] image = changeImageListView(categoria.getNombre());

                producto = new Producto(nombre, descripcion, precio, image, categoria);

                movimiento = new Movimiento(importe, desMovimiento, new Date(), saldo, producto);

                categorias.add(categoria);
                productos.add(producto);
                movimientos.add(movimiento);

                vaciarEditText();

                break;

            case R.id.idPagar:
                Log.d("***", "PAGAR");
                //categoriaListas
                categoriaServices = new CategoriaServicesImpl(this.getActivity());

                productoServices = new ProductoServicesImpl(this.getActivity());

                movimientoServices = new MovimientoServicesImpl(this.getActivity());

                for (int i=0; i< categorias.size(); i++) {

                    categoriaServices.create(categorias.get(i));

                    productoServices.create(productos.get(i));

                    movimientoServices.create(movimientos.get(i));
                }

                break;
            case R.id.idGuardar:

                magicalCamera.takeFragmentPhoto(FormularioFragment.this);


                //fragment = new ListadoDetalleFragment();

                //magicalCamera.takeFragmentPhoto(fragment);

                //magicalCamera.takeFragmentPhoto(FormularioFragment.this);

                //startActivityForResult(magicalCamera.getIntentFragment(), magicalCamera.TAKE_PHOTO);

                break;
        }

    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    private void hacerFoto(){
        /* ****************************
        Esta parte sirve para hacer foto y con el boton botonGuardar guardamos las imagenes que hemos echo: */
        String [] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        magicalPermissions = new MagicalPermissions(this,permissions);
        magicalCamera = new MagicalCamera(this.getActivity(), RESIZE_PHOTO_PIXEL_PERCENTAGE, magicalPermissions);
    }


    /* Funciones a implementar para usar la camara son:
    * 1) */

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("***", "ENTRA AQUI resultCode: ");
        if (resultCode == RESULT_OK){

            magicalCamera.resultPhoto(requestCode, resultCode, data);
            imageView.setImageBitmap(magicalCamera.getPhoto());

            String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),
                          "myPhotoName",
                        "myDirectoryName",
                                      magicalCamera.JPEG,
                    true);


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
        Categoria categoria8 = new Categoria("ALQUILER");
        Categoria categoria7 = new Categoria("EXTRAS");

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);
        categorias.add(categoria6);
        categorias.add(categoria7);
        categorias.add(categoria8);

        int i = 0;
        String [] strNombres = new String [8];

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

        }
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        byte [] imagen = getBytes(bitmap);
        return imagen;
    }

    private void vaciarEditText(){
        editSaldo.setText("");
        editFecha.setText("");
        editDescripcion.setText("");
        editImporte.setText("");
        editPrecio.setText("");
        editNombre.setText("");
        editDesMovimiento.setText("");
    }

}
